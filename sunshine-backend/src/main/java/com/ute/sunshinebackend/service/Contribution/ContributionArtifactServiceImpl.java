package com.ute.sunshinebackend.service.Contribution;

import com.ute.sunshinebackend.dto.ContributionArtifactDto;
import com.ute.sunshinebackend.entity.Contribution.Contribution;
import com.ute.sunshinebackend.entity.Contribution.ContributionArtifact;
import com.ute.sunshinebackend.repository.Contribution.ContributionArtifactRepository;
import com.ute.sunshinebackend.repository.Contribution.ContributionRepository;
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

    @Override
    public ResponseEntity<List<ContributionArtifactDto>> getArtifactsByContributionId(Long contributionId) {
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
                    contributionArtifactListDto.add(contributionArtifactDto);
                }
            }
            return new ResponseEntity<>(contributionArtifactListDto, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
