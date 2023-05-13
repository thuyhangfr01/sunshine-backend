package com.ute.sunshinebackend.service.Form;

import com.ute.sunshinebackend.dto.*;
import com.ute.sunshinebackend.entity.Contribution.Contribution;
import com.ute.sunshinebackend.entity.Contribution.ContributionMoney;
import com.ute.sunshinebackend.entity.Form.FormHelp;
import com.ute.sunshinebackend.entity.Form.FormStatus;
import com.ute.sunshinebackend.entity.Project.Project;
import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Form.FormHelpRepository;
import com.ute.sunshinebackend.repository.Form.FormStatusRepository;
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
public class FormHelpServiceImpl implements FormHelpService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    FormHelpRepository formHelpRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FormStatusRepository formStatusRepository;

    @Override
    public ResponseEntity<List<FormHelpDto>> getLatestFormHelp() {
        try {
            List<FormHelpDto> listDto = new ArrayList<FormHelpDto>();
            List<FormHelp> list = formHelpRepository.findByOrderByCreatedAtDesc();
            for (int i = 0; i < list.size(); i++) {
                FormHelpDto formHelpDto = new FormHelpDto();

                formHelpDto.setUserName(list.get(i).getUser().getName());
                formHelpDto.setUserEmail(list.get(i).getUser().getEmail());
                formHelpDto.setUserPhone(list.get(i).getUser().getPhone());
                formHelpDto.setTitle(list.get(i).getTitle());
                formHelpDto.setContents(list.get(i).getContents());
                formHelpDto.setCreatedAt(list.get(i).getCreatedAt());
                formHelpDto.setStatusName(list.get(i).getFormStatus().getName());
                formHelpDto.setFormImageList(list.get(i).getFormImages());

                listDto.add(formHelpDto);
            }
            return new ResponseEntity<>(listDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<FormHelpDto> getFormHelpById(Long formId) {
        FormHelp formHelp = formHelpRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found form id"));

        FormHelpDto formHelpDto = new FormHelpDto(
                formHelp.getUser().getName(),
                formHelp.getUser().getEmail(),
                formHelp.getUser().getPhone(),
                formHelp.getTitle(),
                formHelp.getContents(),
                formHelp.getFormStatus().getName(),
                formHelp.getCreatedAt(),
                formHelp.getFormImages()
        );

        return new ResponseEntity<>(formHelpDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FormHelpDto>> getLatestFormHelpByUserId(Long userId) {
        List<FormHelp> formEntity = formHelpRepository.findByUserId(userId);
        List<FormHelpDto> formDto = new ArrayList<FormHelpDto>();

        for(int i = 0; i < formEntity.size(); i++){
            FormHelpDto form = new FormHelpDto(
                    formEntity.get(i).getUser().getName(),
                    formEntity.get(i).getUser().getEmail(),
                    formEntity.get(i).getUser().getPhone(),
                    formEntity.get(i).getTitle(),
                    formEntity.get(i).getContents(),
                    formEntity.get(i).getFormStatus().getName(),
                    formEntity.get(i).getCreatedAt(),
                    formEntity.get(i).getFormImages()
            );

            formDto.add(form);
        }
        return new ResponseEntity<>(formDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FormHelpCreatorDto> addFormHelp(FormHelpCreatorDto formHelpCreatorDto) {
        try {
            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            FormHelp formHelpEntity = modelMapper.map(formHelpCreatorDto, FormHelp.class);

            //user
            Optional<User> user = userRepository.findById(formHelpCreatorDto.getUserId());

            formHelpEntity.setTitle(formHelpCreatorDto.getTitle());
            formHelpEntity.setContents(formHelpCreatorDto.getContents());
            formHelpEntity.setUser(user.get());
            formStatusRepository.findById(formHelpCreatorDto.getStatusId()).map(formStatus -> {
                formHelpEntity.setFormStatus(formStatus);

                return formHelpEntity;
            }).orElseThrow(() -> new ResourceNotFoundException("Not found status with id"));

            //convert entity to dto
            FormHelpCreatorDto formHelpCreatorDto1 = modelMapper.map(formHelpRepository.save(formHelpEntity), FormHelpCreatorDto.class);

            return new ResponseEntity<>(formHelpCreatorDto1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<FormHelpCreatorDto> updateFormHelpById(Long formId, FormHelpCreatorDto formHelpCreatorDto) {
        try{
            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            FormHelp formRequest = modelMapper.map(formHelpCreatorDto, FormHelp.class);

            FormHelp formHelp = formHelpRepository.findById(formId)
                    .orElseThrow(() -> new ResourceNotFoundException("Form id " + formId + "not found"));

            formStatusRepository.findById(formHelpCreatorDto.getStatusId()).map(formStatus -> {
                formRequest.setFormStatus(formStatus);

                return formRequest;
            }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

            formHelp.setId(formRequest.getId());
            formHelp.setTitle(formRequest.getTitle());
            formHelp.setContents(formRequest.getContents());

            //convert entity to dto
            FormHelpCreatorDto formHelpCreatorDto1 = modelMapper.map(formHelpRepository.save(formHelp), FormHelpCreatorDto.class);
            return new ResponseEntity<>(formHelpCreatorDto1, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<FormStatus> updateFormHelpStatus(Long formId, FormStatus formStatus) {
        //check co ton tai formId hay khong
        FormHelp formHelp = formHelpRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form id not found"));

        formStatusRepository.findById(formStatus.getId()).map(status -> {
            formHelp.setFormStatus(status);

            return formHelpRepository.save(formHelp);
        }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

        return new ResponseEntity<>(formHelp.getFormStatus(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteFormHelpById(Long formId) {
        try{
            formHelpRepository.deleteById(formId);
            return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}
