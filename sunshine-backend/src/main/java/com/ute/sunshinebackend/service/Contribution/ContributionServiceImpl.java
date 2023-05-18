package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionDto.*;
import com.ute.sunshinebackend.entity.Contribution.Contribution;
import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import com.ute.sunshinebackend.entity.Contribution.ContributionMoney;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.entity.User.User;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Contribution.ContributionArtifactRepository;
import com.ute.sunshinebackend.repository.Contribution.ContributionMoneyRepository;
import com.ute.sunshinebackend.repository.Contribution.ContributionRepository;
import com.ute.sunshinebackend.repository.Contribution.ContributionStatusRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import com.ute.sunshinebackend.repository.User.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContributionServiceImpl implements ContributionService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public ContributionRepository contributionRepository;
    @Autowired
    public ContributionMoneyRepository contributionMoneyRepository;
    @Autowired
    public ContributionArtifactService contributionArtifactService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public ContributionStatusRepository contributionStatusRepository;

    @Override
    public ResponseEntity<List<ContributionDto>> getAllContributions() {
        try {
            List<ContributionDto> listDto = new ArrayList<ContributionDto>();
            List<Contribution> list = contributionRepository.findAll();
            for (int i = 0; i < list.size(); i++) {
                ContributionDto contributionDto = new ContributionDto();

                contributionDto.setId(list.get(i).getId());
                contributionDto.setNickname(list.get(i).getNickname());
                contributionDto.setMessages(list.get(i).getMessages());
                contributionDto.setUserName(list.get(i).getUser().getName());
                contributionDto.setUserEmail(list.get(i).getUser().getEmail());
                contributionDto.setUserPhone(list.get(i).getUser().getPhone());
                contributionDto.setProjectName(list.get(i).getProject().getName());
                contributionDto.setProjectType(list.get(i).getProject().getProjectType().getName());
                contributionDto.setContributionMoney(list.get(i).getContributionMoney().getAmountMoney());
                contributionDto.setMoneyStatus(list.get(i).getContributionMoney().getMcontributionStatus().getName());
                contributionDto.setPaymentType(list.get(i).getPaymentType());
                contributionDto.setCreatedAt(list.get(i).getCreatedAt());

                List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
                contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(list.get(i).getId()).getBody();
                contributionDto.setContributionArtifactDto(contributionArtifactDto);

                listDto.add(contributionDto);
            }
            return new ResponseEntity<>(listDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<ContributionDto>> getAllLatestContributions() {
        try {
            List<ContributionDto> listDto = new ArrayList<ContributionDto>();
            List<Contribution> list = contributionRepository.findByOrderByCreatedAtDesc();
            for (int i = 0; i < list.size(); i++) {
                ContributionDto contributionDto = new ContributionDto();

                contributionDto.setId(list.get(i).getId());
                contributionDto.setNickname(list.get(i).getNickname());
                contributionDto.setMessages(list.get(i).getMessages());
                contributionDto.setUserName(list.get(i).getUser().getName());
                contributionDto.setUserEmail(list.get(i).getUser().getEmail());
                contributionDto.setUserPhone(list.get(i).getUser().getPhone());
                contributionDto.setProjectName(list.get(i).getProject().getName());
                contributionDto.setProjectType(list.get(i).getProject().getProjectType().getName());
                contributionDto.setContributionMoney(list.get(i).getContributionMoney().getAmountMoney());
                contributionDto.setMoneyStatus(list.get(i).getContributionMoney().getMcontributionStatus().getName());
                contributionDto.setPaymentType(list.get(i).getPaymentType());
                contributionDto.setCreatedAt(list.get(i).getCreatedAt());

                List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
                contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(list.get(i).getId()).getBody();
                contributionDto.setContributionArtifactDto(contributionArtifactDto);

                listDto.add(contributionDto);
            }
            return new ResponseEntity<>(listDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ContributionDto> getContributionById(String contributionId) {
        Boolean checkId = contributionRepository.existsById(contributionId);
        ContributionDto contributionDto = new ContributionDto();

        if (checkId) {
            Optional<Contribution> contribution = contributionRepository.findById(contributionId);
            contributionDto.setId(contribution.get().getId());
            contributionDto.setNickname(contribution.get().getNickname());
            contributionDto.setMessages(contribution.get().getMessages());
            contributionDto.setUserName(contribution.get().getUser().getName());
            contributionDto.setUserEmail(contribution.get().getUser().getEmail());
            contributionDto.setUserPhone(contribution.get().getUser().getPhone());
            contributionDto.setProjectName(contribution.get().getProject().getName());
            contributionDto.setProjectType(contribution.get().getProject().getProjectType().getName());
            contributionDto.setContributionMoney(contribution.get().getContributionMoney().getAmountMoney());
            contributionDto.setMoneyStatus(contribution.get().getContributionMoney().getMcontributionStatus().getName());
            contributionDto.setPaymentType(contribution.get().getPaymentType());
            contributionDto.setCreatedAt(contribution.get().getCreatedAt());

            List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
            contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(contribution.get().getId()).getBody();
            if (contributionArtifactDto != null) {
                contributionDto.setContributionArtifactDto(contributionArtifactDto);
            }
            return new ResponseEntity<>(contributionDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<ContributionDto>> getAllContributionByUserId(Long userId) {
        try {
            List<Contribution> list = contributionRepository.findByUserId(userId);
            List<ContributionDto> contributionDtoList = new ArrayList<ContributionDto>();

            for (int i = 0; i < list.size(); i++) {
                ContributionDto contributionDto = new ContributionDto();

                contributionDto.setId(list.get(i).getId());
                contributionDto.setNickname(list.get(i).getNickname());
                contributionDto.setMessages(list.get(i).getMessages());
                contributionDto.setUserName(list.get(i).getUser().getName());
                contributionDto.setUserEmail(list.get(i).getUser().getEmail());
                contributionDto.setUserPhone(list.get(i).getUser().getPhone());
                contributionDto.setProjectName(list.get(i).getProject().getName());
                contributionDto.setProjectType(list.get(i).getProject().getProjectType().getName());
                contributionDto.setContributionMoney(list.get(i).getContributionMoney().getAmountMoney());
                contributionDto.setMoneyStatus(list.get(i).getContributionMoney().getMcontributionStatus().getName());
                contributionDto.setPaymentType(list.get(i).getPaymentType());
                contributionDto.setCreatedAt(list.get(i).getCreatedAt());

                List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
                contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(list.get(i).getId()).getBody();
                if (contributionArtifactDto != null) {
                    contributionDto.setContributionArtifactDto(contributionArtifactDto);
                }

                contributionDtoList.add(contributionDto);
            }
            return new ResponseEntity<>(contributionDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ContributionDto>> getAllContributionByProjectId(Long projectId) {
        try {
            List<Contribution> list = contributionRepository.findByProjectId(projectId);
            List<ContributionDto> contributionDtoList = new ArrayList<ContributionDto>();

            for (int i = 0; i < list.size(); i++) {
                ContributionDto contributionDto = new ContributionDto();

                contributionDto.setId(list.get(i).getId());
                contributionDto.setNickname(list.get(i).getNickname());
                contributionDto.setMessages(list.get(i).getMessages());
                contributionDto.setUserName(list.get(i).getUser().getName());
                contributionDto.setUserEmail(list.get(i).getUser().getEmail());
                contributionDto.setUserPhone(list.get(i).getUser().getPhone());
                contributionDto.setProjectName(list.get(i).getProject().getName());
                contributionDto.setProjectType(list.get(i).getProject().getProjectType().getName());
                contributionDto.setContributionMoney(list.get(i).getContributionMoney().getAmountMoney());
                contributionDto.setMoneyStatus(list.get(i).getContributionMoney().getMcontributionStatus().getName());
                contributionDto.setPaymentType(list.get(i).getPaymentType());
                contributionDto.setCreatedAt(list.get(i).getCreatedAt());

                List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
                contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(list.get(i).getId()).getBody();
                if (contributionArtifactDto != null) {
                    contributionDto.setContributionArtifactDto(contributionArtifactDto);
                }

                contributionDtoList.add(contributionDto);
            }
            return new ResponseEntity<>(contributionDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ContributionCreatorDto> addContribution(ContributionCreatorDto contributionCreatorDto) {

//        try {
            //money
            ContributionMoney contributionMoneyEntity = new ContributionMoney();
            contributionMoneyEntity.setAmountMoney(contributionCreatorDto.getAmountMoney());
            contributionMoneyEntity.setMcontributionStatus(contributionStatusRepository.findById(
                    contributionCreatorDto.getStatusMoneyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Status not found")));
            contributionMoneyEntity = contributionMoneyRepository.save(contributionMoneyEntity);

            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            Contribution contributionEntity = modelMapper.map(contributionCreatorDto, Contribution.class);

            //artifacts
            List<ContributionArtifact> artifactsNew = new ArrayList<ContributionArtifact>();
            List<ContributionArtifact> artifactCreator = contributionCreatorDto.getContributionArtifacts();
            if(artifactCreator != null){
                for(int i = 0; i < artifactCreator.size(); i++){
                    ContributionArtifact item = new ContributionArtifact();
                    item.setArtifactName(artifactCreator.get(i).getArtifactName());
                    item.setDonatedAmount(artifactCreator.get(i).getDonatedAmount());
                    item.setReceivedAmount(0L);
                    item.setCalculationUnit(artifactCreator.get(i).getCalculationUnit());
                    item.setContributionStatus(contributionStatusRepository.findById(
                                    contributionCreatorDto.getStatueArtifactId())
                            .orElseThrow(() -> new ResourceNotFoundException("Status not found")));

                    artifactsNew.add(item);
                }
                contributionEntity.addArtifact(artifactsNew);
            }

        //user
            Optional<User> user = userRepository.findById(contributionCreatorDto.getUserId());

            //project
            Optional<Project> project = projectRepository.findById(contributionCreatorDto.getProjectId());

            contributionEntity.setNickname(contributionCreatorDto.getNickname());
            contributionEntity.setMessages(contributionCreatorDto.getMessages());
            contributionEntity.setContributionMoney(contributionMoneyEntity);
            contributionEntity.setUser(user.get());
            contributionEntity.setProject(project.get());
            contributionEntity.setPaymentType(contributionCreatorDto.getPaymentType());

            contributionCreatorDto.setProjectName(project.get().getName());
            //convert entity to dto
            ContributionCreatorDto contributionDto = modelMapper.map(contributionRepository.save(contributionEntity), ContributionCreatorDto.class);

            return new ResponseEntity<>(contributionDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @Override
    public ResponseEntity<ContributionMoneyUpdateDto> updateMoneyById(Long id, ContributionMoneyUpdateDto contributionMoneyUpdateDto) {
        //convert dto to entity
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ContributionMoney contributionMoneyRequest = modelMapper.map(contributionMoneyUpdateDto, ContributionMoney.class);

        //check xem co ton tai contirbutionId khong
//        Contribution contribution = contributionRepository.findById(contributionId)
//                .orElseThrow(() -> new ResourceNotFoundException("Contribution id " + contributionId + "not found"));
//        contributionMoneyUpdateDto.setContributionId(contributionId);

        //check xem co ton tai id cua contributionMoney khong
        ContributionMoney contributionMoney = contributionMoneyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution money id not found"));
        contributionMoneyUpdateDto.setMoneyId(id);

        //cap nhat so tien
        contributionMoney.setAmountMoney(contributionMoneyRequest.getAmountMoney());

        //convert entity to dto
        ContributionMoneyUpdateDto _contributionMoneyUpdateDto = modelMapper.map(contributionMoneyRepository.save(contributionMoney), ContributionMoneyUpdateDto.class);

        return new ResponseEntity<>(_contributionMoneyUpdateDto, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<StatusMoneyDto> updateStatusMoney(Long contributionMoneyId, StatusMoneyDto statusMoneyDto) {
        //check co ton tai contribution money id hay khong
        ContributionMoney contributionMoney = contributionMoneyRepository.findById(contributionMoneyId)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution money id not found"));

        contributionStatusRepository.findById(statusMoneyDto.getStatusId()).map(contributionStatus1 -> {
            contributionMoney.setMcontributionStatus(contributionStatus1);

            return contributionMoneyRepository.save(contributionMoney);
        }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

        statusMoneyDto.setMoneyId(contributionMoneyId);
        statusMoneyDto.setContributionStatus(contributionMoney.getMcontributionStatus());

        return new ResponseEntity<>(statusMoneyDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteContribution(String contributionId) {
//        try {
            contributionRepository.deleteById(contributionId);
            return new ResponseEntity<>(null, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
    }
}

