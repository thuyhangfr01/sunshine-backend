package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionDto.ContributionArtifactCreatorDto;
import com.ute.sunshinebackend.dto.ContributionDto.ContributionArtifactDto;
import com.ute.sunshinebackend.entity.Contribution.Contribution;
import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
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
    public ResponseEntity<List<ContributionArtifactDto>> getArtifactsByContributionId(String contributionId) {
        try{
            List<ContributionArtifactDto> contributionArtifactListDto = new ArrayList<ContributionArtifactDto>();
            //check xem co ton tai contributionId khong?
            Boolean checkId  = contributionRepository.existsById(contributionId);
            if(checkId){
                Optional<Contribution> contribution = contributionRepository.findById(contributionId);
                List<ContributionArtifact> contributionArtifactList = contribution.get().getContributionArtifacts();
                for(int i = 0; i < contributionArtifactList.size(); i++){
                    ContributionArtifactDto contributionArtifactDto = new ContributionArtifactDto();
                    contributionArtifactDto.setArtifactName(contributionArtifactList.get(i).getArtifactName());
                    contributionArtifactDto.setDonatedAmount(contributionArtifactList.get(i).getDonatedAmount());
                    contributionArtifactDto.setReceivedAmount(contributionArtifactList.get(i).getReceivedAmount());
                    contributionArtifactDto.setCalculationUnit(contributionArtifactList.get(i).getCalculationUnit());
                    contributionArtifactDto.setArtifactStatus(contributionArtifactList.get(i).getContributionStatus().getName());
                    contributionArtifactDto.setContributionId(contributionId);
                    contributionArtifactListDto.add(contributionArtifactDto);
                }
            }
            return new ResponseEntity<>(contributionArtifactListDto, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @Override
    public ResponseEntity<ContributionArtifact> updateArtifactById(Long artifactId, ContributionArtifact contributionArtifact) {
        try{
            //check artifact id co ton tai khong
            ContributionArtifact artifact = contributionArtifactRepository.findById(artifactId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found artifact id"));

            artifact.setId(contributionArtifact.getId());
            artifact.setArtifactName(contributionArtifact.getArtifactName());
            artifact.setDonatedAmount(contributionArtifact.getDonatedAmount());
            artifact.setReceivedAmount(contributionArtifact.getReceivedAmount());
            artifact.setCalculationUnit(contributionArtifact.getCalculationUnit());
            contributionStatusRepository.findById(contributionArtifact.getContributionStatus().getId()).map(contributionStatus -> {
                artifact.setContributionStatus(contributionStatus);

                return contributionArtifactRepository.save(artifact);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found status with id"));

            return new ResponseEntity<>(artifact, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
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
