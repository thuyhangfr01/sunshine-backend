package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.dto.ProjectDto.ProjectPaymentCreatorDto;
import com.ute.sunshinebackend.dto.ProjectDto.ProjectPaymentDto;
import com.ute.sunshinebackend.dto.ProjectDto.UnionDto;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.entity.Project.ProjectPayment;
import com.ute.sunshinebackend.entity.User.User;
import com.ute.sunshinebackend.repository.Contribution.ContributionRepository;
import com.ute.sunshinebackend.repository.Project.ProjectPaymentRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import com.ute.sunshinebackend.repository.User.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class ProjectPaymentServiceImpl implements ProjectPaymentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public ProjectPaymentRepository projectPaymentRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public ContributionRepository contributionRepository;
    @Override
    public ResponseEntity<List<ProjectPaymentDto>> getAllPaymentByProjectId(long projectId) {
        try {
            List<ProjectPayment> list = projectPaymentRepository.findByProjectId(projectId);
            List<ProjectPaymentDto> projectPaymentDtoList = new ArrayList<ProjectPaymentDto>();

            for (int i = 0; i < list.size(); i++) {
                ProjectPaymentDto projectPaymentDto = new ProjectPaymentDto();

                projectPaymentDto.setId(list.get(i).getId());
                projectPaymentDto.setAmountMoney(list.get(i).getAmountMoney());
                projectPaymentDto.setReason(list.get(i).getReason());
                projectPaymentDto.setCreatedAt(list.get(i).getCreatedAt());
                projectPaymentDto.setProjectName(list.get(i).getProject().getName());
                projectPaymentDto.setUserName(list.get(i).getUser().getName());

                projectPaymentDtoList.add(projectPaymentDto);
            }
            return new ResponseEntity<>(projectPaymentDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ProjectPaymentDto> getProjectPaymentById(String id) {
        Boolean checkId = projectPaymentRepository.existsById(id);
        ProjectPaymentDto projectPaymentDto = new ProjectPaymentDto();

        if (checkId) {
            Optional<ProjectPayment> projectPayment = projectPaymentRepository.findById(id);
            projectPaymentDto.setId(projectPayment.get().getId());
            projectPaymentDto.setAmountMoney(projectPayment.get().getAmountMoney());
            projectPaymentDto.setReason(projectPayment.get().getReason());
            projectPaymentDto.setUserName(projectPayment.get().getUser().getName());
            projectPaymentDto.setProjectName(projectPayment.get().getProject().getName());
            projectPaymentDto.setCreatedAt(projectPayment.get().getCreatedAt());

            return new ResponseEntity<>(projectPaymentDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<ProjectPaymentCreatorDto> createProjectPayment(ProjectPaymentCreatorDto projectPaymentCreatorDto) {
        //convert dto to entity
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ProjectPayment projectPaymentEntity = modelMapper.map(projectPaymentCreatorDto, ProjectPayment.class);

        //user
        Optional<User> user = userRepository.findById(projectPaymentCreatorDto.getUserId());

        //project
        Optional<Project> project = projectRepository.findById(projectPaymentCreatorDto.getProjectId());

        projectPaymentEntity.setUser(user.get());
        projectPaymentEntity.setProject(project.get());
        projectPaymentEntity.setAmountMoney(projectPaymentCreatorDto.getAmountMoney());
        projectPaymentEntity.setReason(projectPaymentCreatorDto.getReason());
        projectPaymentEntity.setCreatedAt(projectPaymentCreatorDto.getCreatedAt());
        projectPaymentCreatorDto.setUserName(user.get().getName());

        //convert entity to dto
        ProjectPaymentCreatorDto projectPaymentCreatorDto1 = modelMapper.map(projectPaymentRepository.save(projectPaymentEntity), ProjectPaymentCreatorDto.class);

        return new ResponseEntity<>(projectPaymentCreatorDto1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UnionDto>> unionByDate(String fromDate1, String toDate1, String fromDate2, String toDate2) {
        List<UnionDto> unionDtos = new ArrayList<>();
        List<Object []> resultUnion = projectPaymentRepository.unionByDate(fromDate1, toDate1, fromDate2, toDate2);

        for(Object[] row : resultUnion){
            UnionDto unionDto = new UnionDto();
            unionDto.setId((String) row[0]);
            unionDto.setAmountMoney((BigDecimal) row[1]);
            unionDto.setUserName((String) row[2]);
            unionDto.setType((String) row[3]);
            unionDto.setCreatedAt((Date) row[4]);
            unionDto.setProjectId((Integer) row[5]);

            unionDtos.add(unionDto);
        }

        return new ResponseEntity<>(unionDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UnionDto>> unionByProjectIdByDate(Integer projectId1, Integer projectId2, String fromDate1, String toDate1, String fromDate2, String toDate2) {
        List<UnionDto> unionDtos = new ArrayList<>();
        if((projectId1 == 0 || projectId2 == 0)
                && (fromDate1.length() == 1|| toDate1.length() == 1 || fromDate2.length() == 1 || toDate2.length() == 1)) {
            List<Object []> resultUnion = projectPaymentRepository.union();

            for(Object[] row : resultUnion){
                UnionDto unionDto = new UnionDto();
                unionDto.setId((String) row[0]);
                unionDto.setAmountMoney((BigDecimal) row[1]);
                unionDto.setUserName((String) row[2]);
                unionDto.setType((String) row[3]);
                unionDto.setCreatedAt((Date) row[4]);
                unionDto.setProjectId((Integer) row[5]);

                unionDtos.add(unionDto);
            }
        }
        else if((projectId1 == 0 || projectId2 == 0) &&
                (fromDate1.length() != 1 && toDate1.length() != 1 && fromDate2.length() != 1 && toDate2.length() != 1)){
            List<Object []> resultUnion = projectPaymentRepository.unionByDate(fromDate1, toDate1, fromDate2, toDate2);

            for(Object[] row : resultUnion){
                UnionDto unionDto = new UnionDto();
                unionDto.setId((String) row[0]);
                unionDto.setAmountMoney((BigDecimal) row[1]);
                unionDto.setUserName((String) row[2]);
                unionDto.setType((String) row[3]);
                unionDto.setCreatedAt((Date) row[4]);
                unionDto.setProjectId((Integer) row[5]);

                unionDtos.add(unionDto);
            }
        } else if((projectId1 != 0 && projectId2 != 0) &&
                (fromDate1.length() == 1|| toDate1.length() == 1 || fromDate2.length() == 1 || toDate2.length() == 1)){
            List<Object []> resultUnion = projectPaymentRepository.unionByProjectId(projectId1, projectId2);

            for(Object[] row : resultUnion){
                UnionDto unionDto = new UnionDto();
                unionDto.setId((String) row[0]);
                unionDto.setAmountMoney((BigDecimal) row[1]);
                unionDto.setUserName((String) row[2]);
                unionDto.setType((String) row[3]);
                unionDto.setCreatedAt((Date) row[4]);
                unionDto.setProjectId((Integer) row[5]);

                unionDtos.add(unionDto);
            }
        } else if((projectId1 != 0 && projectId2 != 0) &&
                (fromDate1.length() != 1 && toDate1.length() != 1 && fromDate2.length() != 1 && toDate2.length() != 1)){
            List<Object []> resultUnion = projectPaymentRepository.unionByDateByProjectId(projectId1, projectId2, fromDate1, toDate1, fromDate2, toDate2);

            for(Object[] row : resultUnion){
                UnionDto unionDto = new UnionDto();
                unionDto.setId((String) row[0]);
                unionDto.setAmountMoney((BigDecimal) row[1]);
                unionDto.setUserName((String) row[2]);
                unionDto.setType((String) row[3]);
                unionDto.setCreatedAt((Date) row[4]);
                unionDto.setProjectId((Integer) row[5]);

                unionDtos.add(unionDto);
            }
        }
        return new ResponseEntity<>(unionDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UnionDto>> union() {
        List<UnionDto> unionDtos = new ArrayList<>();
        List<Object []> resultUnion = projectPaymentRepository.union();

        for(Object[] row : resultUnion){
            UnionDto unionDto = new UnionDto();
            unionDto.setId((String) row[0]);
            unionDto.setAmountMoney((BigDecimal) row[1]);
            unionDto.setUserName((String) row[2]);
            unionDto.setType((String) row[3]);
            unionDto.setCreatedAt((Date) row[4]);
            unionDto.setProjectId((Integer) row[5]);

            unionDtos.add(unionDto);
        }
            return new ResponseEntity<>(unionDtos, HttpStatus.OK);
    }


}
