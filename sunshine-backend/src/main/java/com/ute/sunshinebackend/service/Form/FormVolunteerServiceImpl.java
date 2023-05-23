package com.ute.sunshinebackend.service.Form;

import com.ute.sunshinebackend.dto.FormDto.FormVolunteerCreatorDto;
import com.ute.sunshinebackend.dto.FormDto.FormVolunteerDto;
import com.ute.sunshinebackend.entity.Form.FormVolunteer;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Form.FormStatusRepository;
import com.ute.sunshinebackend.repository.Form.FormVolunteerRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import com.ute.sunshinebackend.service.Mail.FormVolunteer.MailVolunteerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormVolunteerServiceImpl implements FormVolunteerService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public MailVolunteerService mailVolunteerService;

    @Autowired
    FormVolunteerRepository formVolunteerRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    FormStatusRepository formStatusRepository;

    @Override
    public ResponseEntity<List<FormVolunteerDto>> getLatestFormVolunteer() {
        try {
            List<FormVolunteerDto> listDto = new ArrayList<FormVolunteerDto>();
            List<FormVolunteer> list = formVolunteerRepository.findByOrderByCreatedAtDesc();
            for (int i = 0; i < list.size(); i++) {
                FormVolunteerDto formVolunteerDto = new FormVolunteerDto();

                formVolunteerDto.setEmail(list.get(i).getEmail());
                formVolunteerDto.setPhone(list.get(i).getPhone());
                formVolunteerDto.setProjectName(list.get(i).getProject().getName());
                formVolunteerDto.setStatusName(list.get(i).getFormStatus().getName());
                formVolunteerDto.setCreatedAt(list.get(i).getCreatedAt());

                listDto.add(formVolunteerDto);
            }
            return new ResponseEntity<>(listDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<FormVolunteerDto> getFormVolunteerById(Long id) {
        FormVolunteer formVolunteer = formVolunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found form id"));

        FormVolunteerDto formVolunteerDto = new FormVolunteerDto(
                formVolunteer.getEmail(),
                formVolunteer.getPhone(),
                formVolunteer.getProject().getName(),
                formVolunteer.getFormStatus().getName(),
                formVolunteer.getCreatedAt()
        );

        return new ResponseEntity<>(formVolunteerDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FormVolunteerCreatorDto> addFormVolunteer(FormVolunteerCreatorDto formVolunteerCreatorDto) {
        try {
            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            FormVolunteer formVolunteerEntity = modelMapper.map(formVolunteerCreatorDto, FormVolunteer.class);

            //user
            Optional<Project> project = projectRepository.findById(formVolunteerCreatorDto.getProjectId());

            formVolunteerEntity.setProject(project.get());
            formVolunteerEntity.setEmail(formVolunteerCreatorDto.getEmail());
            formVolunteerEntity.setPhone(formVolunteerCreatorDto.getPhone());
            formStatusRepository.findById(formVolunteerCreatorDto.getStatusId()).map(formStatus -> {
                formVolunteerEntity.setFormStatus(formStatus);

                return formVolunteerEntity;
            }).orElseThrow(() -> new ResourceNotFoundException("Not found status with id"));

            //convert entity to dto
            FormVolunteerCreatorDto formVolunteerCreatorDto1 = modelMapper.map(formVolunteerRepository.save(formVolunteerEntity), FormVolunteerCreatorDto.class);

            return new ResponseEntity<>(formVolunteerCreatorDto1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<FormVolunteerCreatorDto> updateFormVolunteerStatus(Long formId, FormVolunteerCreatorDto formVolunteerCreatorDto) {
        //convert dto to entity
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        FormVolunteer formVolunteerEntity = modelMapper.map(formVolunteerCreatorDto, FormVolunteer.class);

        //check co ton tai formId hay khong
        FormVolunteer formVolunteer = formVolunteerRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form id not found"));

        if(formVolunteerCreatorDto.getStatusId() == 2){
            formStatusRepository.findById(formVolunteerCreatorDto.getId()).map(status -> {
                formVolunteer.setFormStatus(status);

                return formVolunteer;
            }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

            projectRepository.findById(formVolunteerCreatorDto.getProjectId()).map(project -> {
                formVolunteer.setProject(project);

                return formVolunteer;
            }).orElseThrow(() -> new ResourceNotFoundException("Not project with id"));

            formVolunteer.setId(formVolunteerEntity.getId());
            formVolunteer.setEmail(formVolunteer.getEmail());
            formVolunteer.setPhone(formVolunteer.getPhone());

            mailVolunteerService.sendMail(formVolunteer.getEmail(), formVolunteer.getProject().getName(), "đã được duyệt");
        } else if (formVolunteerCreatorDto.getStatusId() == 3){
            formStatusRepository.findById(formVolunteerCreatorDto.getId()).map(status -> {
                formVolunteer.setFormStatus(status);

                return formVolunteer;
            }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

            projectRepository.findById(formVolunteerCreatorDto.getProjectId()).map(project -> {
                formVolunteer.setProject(project);

                return formVolunteer;
            }).orElseThrow(() -> new ResourceNotFoundException("Not project with id"));

            formVolunteer.setId(formVolunteerEntity.getId());
            formVolunteer.setEmail(formVolunteer.getEmail());
            formVolunteer.setPhone(formVolunteer.getPhone());

            mailVolunteerService.sendMail(formVolunteer.getEmail(), formVolunteer.getProject().getName(), "đã bị từ chối");
        }

        //convert entity to dto
        FormVolunteerCreatorDto formVolunteerCreatorDto1 = modelMapper.map(formVolunteerRepository.save(formVolunteer), FormVolunteerCreatorDto.class);

        return new ResponseEntity<>(formVolunteerCreatorDto1, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteFormVolunteerById(Long formId) {
        try{
            formVolunteerRepository.deleteById(formId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
