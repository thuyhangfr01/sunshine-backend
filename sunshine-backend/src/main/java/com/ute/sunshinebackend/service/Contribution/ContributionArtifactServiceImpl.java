package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionDto.ContributionArtifactCreatorDto;
import com.ute.sunshinebackend.dto.ContributionDto.ContributionArtifactListDto;
import com.ute.sunshinebackend.dto.ContributionDto.ContributionArtifactDto;
import com.ute.sunshinebackend.dto.ContributionDto.StatusArtifactDto;
import com.ute.sunshinebackend.entity.Contribution.Contribution;
import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import com.ute.sunshinebackend.entity.Contribution.ContributionMoney;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Contribution.ContributionArtifactRepository;
import com.ute.sunshinebackend.repository.Contribution.ContributionRepository;
import com.ute.sunshinebackend.repository.Contribution.ContributionStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContributionArtifactServiceImpl implements ContributionArtifactService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ContributionRepository contributionRepository;

    @Autowired
    ContributionArtifactRepository contributionArtifactRepository;

    @Autowired
    ContributionStatusRepository contributionStatusRepository;

    @Override
    public ResponseEntity<List<ContributionArtifactListDto>> getContributionArtifacts() {
        List<ContributionArtifactListDto> contributionArtifactListDtos = new ArrayList<>();
        List<Object []> result = contributionArtifactRepository.listContributionArtifacts();

        for(Object[] row : result){
            ContributionArtifactListDto contributionArtifactListDto = new ContributionArtifactListDto();
            contributionArtifactListDto.setId((String) row[0]);
            contributionArtifactListDto.setUserName((String) row[1]);
            contributionArtifactListDto.setProjectName((String) row[2]);
            contributionArtifactListDto.setCreatedAt((Date) row[3]);

            contributionArtifactListDtos.add(contributionArtifactListDto);
        }
        return new ResponseEntity<>(contributionArtifactListDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContributionArtifactListDto>> getContributionArtifactsByUserId(Long userId) {
        List<ContributionArtifactListDto> contributionArtifactListDtos = new ArrayList<>();
        List<Object []> result = contributionArtifactRepository.listContributionArtifactsByUserId(userId);

        for(Object[] row : result){
            ContributionArtifactListDto contributionArtifactListDto = new ContributionArtifactListDto();
            contributionArtifactListDto.setId((String) row[0]);
            contributionArtifactListDto.setUserName((String) row[1]);
            contributionArtifactListDto.setProjectName((String) row[2]);
            contributionArtifactListDto.setCreatedAt((Date) row[3]);

            contributionArtifactListDtos.add(contributionArtifactListDto);
        }
        return new ResponseEntity<>(contributionArtifactListDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContributionArtifactDto>> getArtifactsByContributionId(String contributionId) {
        List<ContributionArtifactDto> contributionArtifactDtos = new ArrayList<>();
        List<Object []> result = contributionArtifactRepository.listArtifactsByContributionId(contributionId);

        for(Object[] row : result){
            ContributionArtifactDto contributionArtifactDto = new ContributionArtifactDto();
            contributionArtifactDto.setContributionId((String) row[0]);
            contributionArtifactDto.setArtifactId((Integer) row[1]);
            contributionArtifactDto.setArtifactName((String) row[2]);
            contributionArtifactDto.setDonatedAmount((Integer) row[3]);
            contributionArtifactDto.setReceivedAmount((Integer) row[4]);
            contributionArtifactDto.setCalculationUnit((String) row[5]);
            contributionArtifactDto.setArtifactStatus((String) row[6]);

            contributionArtifactDtos.add(contributionArtifactDto);
        }
        return new ResponseEntity<>(contributionArtifactDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContributionArtifactCreatorDto> addNewArtifactByContributionId(String contributionId, ContributionArtifactCreatorDto contributionArtifactCreatorDto) {
//        try{
            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            ContributionArtifact contributionArtifact = modelMapper.map(contributionArtifactCreatorDto, ContributionArtifact.class);

            Contribution contribution = contributionRepository.findById(contributionId)
                            .orElseThrow(() -> new ResourceNotFoundException("Not found contribution"));
            contributionStatusRepository.findById(contributionArtifactCreatorDto.getStatusArtifactId()).map(contributionStatus -> {
                contributionArtifact.setContributionStatus(contributionStatus);

                return contributionArtifact;
            }).orElseThrow(() -> new ResourceNotFoundException("Not found status with id"));

            contributionArtifact.setArtifactName(contributionArtifactCreatorDto.getArtifactName());
            contributionArtifact.setDonatedAmount(contributionArtifactCreatorDto.getDonatedAmount());
            contributionArtifact.setCalculationUnit(contributionArtifactCreatorDto.getCalculationUnit());
            contributionArtifact.setContribution(contribution);

            //convert entity to dto
            ContributionArtifactCreatorDto _contributionArtifactCreatorDto = modelMapper.map(contributionArtifactRepository.save(contributionArtifact), ContributionArtifactCreatorDto.class);

            return new ResponseEntity<>(_contributionArtifactCreatorDto, HttpStatus.CREATED);
//        } catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

//    @Override
//    public ResponseEntity<ContributionArtifact> updateArtifactById(Long artifactId, ContributionArtifact contributionArtifact) {
//        try{
//            //check artifact id co ton tai khong
//            ContributionArtifact artifact = contributionArtifactRepository.findById(artifactId)
//                    .orElseThrow(() -> new ResourceNotFoundException("Not found artifact id"));
//
//            artifact.setId(contributionArtifact.getId());
//            artifact.setArtifactName(contributionArtifact.getArtifactName());
//            artifact.setDonatedAmount(contributionArtifact.getDonatedAmount());
//            artifact.setReceivedAmount(contributionArtifact.getReceivedAmount());
//            artifact.setCalculationUnit(contributionArtifact.getCalculationUnit());
//            contributionStatusRepository.findById(contributionArtifact.getContributionStatus().getId()).map(contributionStatus -> {
//                artifact.setContributionStatus(contributionStatus);
//
//                return contributionArtifactRepository.save(artifact);
//            }).orElseThrow(() -> new ResourceNotFoundException("Not found status with id"));
//
//            return new ResponseEntity<>(artifact, HttpStatus.CREATED);
//        } catch(Exception e){
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

    @Override
    public ResponseEntity<StatusArtifactDto> updateArtifactById(Long artifactId, StatusArtifactDto statusArtifactDto) {
        //check co ton tai contribution money id hay khong
        ContributionArtifact contributionArtifact = contributionArtifactRepository.findById(artifactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution artifact id not found"));

        contributionStatusRepository.findById(statusArtifactDto.getStatusId()).map(contributionStatus1 -> {
            contributionArtifact.setContributionStatus(contributionStatus1);

            return contributionArtifact;
        }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

        statusArtifactDto.setArtifactId(artifactId);
        statusArtifactDto.setContributionStatus(contributionArtifact.getContributionStatus());
        contributionArtifact.setReceivedAmount(statusArtifactDto.getReceivedAmount());

        contributionArtifactRepository.save(contributionArtifact);

        return new ResponseEntity<>(statusArtifactDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteArtifactById(Long artifactId) {
        try{
            contributionArtifactRepository.deleteById(artifactId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
