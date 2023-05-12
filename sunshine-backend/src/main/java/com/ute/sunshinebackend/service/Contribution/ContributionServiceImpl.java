package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.*;
import com.ute.sunshinebackend.entity.Contribution.Contribution;
import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import com.ute.sunshinebackend.entity.Contribution.ContributionMoney;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.repository.Contribution.ContributionRepository;
import com.ute.sunshinebackend.repository.Project.ProjectRepository;
import com.ute.sunshinebackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContributionServiceImpl implements ContributionService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public ContributionRepository contributionRepository;

    @Autowired
    public ContributionArtifactService contributionArtifactService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProjectRepository projectRepository;

    @Override
    public ResponseEntity<List<ContributionDto>> getAllContributions() {
        try{
            List<ContributionDto> listDto = new ArrayList<ContributionDto>();
            List<Contribution>  list = contributionRepository.findAll();
            for(int i = 0;i < list.size(); i++){
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
                contributionDto.setMoneyStatus(list.get(i).getContributionMoney().getContributionStatus().getName());

                List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
                contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(list.get(i).getId()).getBody();
                if(contributionArtifactDto != null){
                    contributionDto.setContributionArtifactDto(contributionArtifactDto);
                }

                listDto.add(contributionDto);
            }
            return new ResponseEntity<>(listDto, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ContributionDto> getContributionById(Long contributionId) {
        Boolean checkId = contributionRepository.existsById(contributionId);
        ContributionDto contributionDto = new ContributionDto();

        if (checkId){
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
            contributionDto.setMoneyStatus(contribution.get().getContributionMoney().getContributionStatus().getName());

            List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
            contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(contribution.get().getId()).getBody();
            if(contributionArtifactDto != null){
                contributionDto.setContributionArtifactDto(contributionArtifactDto);
            }
            return new ResponseEntity<>(contributionDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<ContributionDto>> getAllContributionByUserId(Long userId) {
        try{
            List<Contribution> list = contributionRepository.findByUserId(userId);
            List<ContributionDto> contributionDtoList = new ArrayList<ContributionDto>();

            for(int i = 0; i < list.size(); i++){
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
                contributionDto.setMoneyStatus(list.get(i).getContributionMoney().getContributionStatus().getName());

                List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
                contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(list.get(i).getId()).getBody();
                if(contributionArtifactDto != null){
                    contributionDto.setContributionArtifactDto(contributionArtifactDto);
                }

                contributionDtoList.add(contributionDto);
            }
            return new ResponseEntity<>(contributionDtoList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ContributionDto>> getAllContributionByProjectId(Long projectId) {
        try{
            List<Contribution> list = contributionRepository.findByProjectId(projectId);
            List<ContributionDto> contributionDtoList = new ArrayList<ContributionDto>();

            for(int i = 0; i < list.size(); i++){
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
                contributionDto.setMoneyStatus(list.get(i).getContributionMoney().getContributionStatus().getName());

                List<ContributionArtifactDto> contributionArtifactDto = new ArrayList<ContributionArtifactDto>();
                contributionArtifactDto = contributionArtifactService.getArtifactsByContributionId(list.get(i).getId()).getBody();
                if(contributionArtifactDto != null){
                    contributionDto.setContributionArtifactDto(contributionArtifactDto);
                }

                contributionDtoList.add(contributionDto);
            }
            return new ResponseEntity<>(contributionDtoList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ContributionCreatorDto> addContribution(ContributionCreatorDto contributionCreatorDto) {
//        try{
            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            Contribution contributionRequest = modelMapper.map(contributionCreatorDto, Contribution.class);

            //money
            ContributionMoney contributionMoney = new ContributionMoney(contributionCreatorDto.getAmount_money());

            //artifact
            List<ContributionArtifact> contributionArtifacts = new ArrayList<ContributionArtifact>();
            List<ContributionArtifactCreatorDto> listDto = contributionCreatorDto.getContributionArtifactCreatorDto();

            for(int i = 0; i < listDto.size(); i++){
                ContributionArtifact artifact = new ContributionArtifact(
                        listDto.get(i).getArtifactName(),
                        listDto.get(i).getDonatedAmount(),
                        listDto.get(i).getCalculationUnit()
                );

                contributionArtifacts.add(artifact);
            }

            //user
            Optional<User> user = userRepository.findById(contributionCreatorDto.getUserId());

            //project
            Optional<Project> project = projectRepository.findById(contributionCreatorDto.getProjectId());

            contributionRequest.setNickname(contributionCreatorDto.getNickname());
            contributionRequest.setMessages(contributionCreatorDto.getMessages());
            contributionRequest.setContributionMoney(contributionMoney);
            contributionRequest.setContributionArtifacts(contributionArtifacts);
            contributionRequest.setUser(user.get());
            contributionRequest.setProject(project.get());

            //convert entity to dto
            ContributionCreatorDto contributionResponse = modelMapper.map(contributionRepository.save(contributionRequest), ContributionCreatorDto.class);

            return new ResponseEntity<>(contributionResponse, HttpStatus.CREATED);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

}
