package com.ute.sunshinebackend.service.Form;

import com.ute.sunshinebackend.dto.FormDto.FormHelpCreatorDto;
import com.ute.sunshinebackend.dto.FormDto.FormHelpDto;
import com.ute.sunshinebackend.entity.Form.FormHelp;
import com.ute.sunshinebackend.exception.ResourceNotFoundException;
import com.ute.sunshinebackend.repository.Form.FormHelpRepository;
import com.ute.sunshinebackend.repository.Form.FormStatusRepository;
import com.ute.sunshinebackend.repository.User.UserRepository;
import com.ute.sunshinebackend.service.Mail.FormHelp.MailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormHelpServiceImpl implements FormHelpService{
    @Autowired
    public MailService mailService;
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

                formHelpDto.setId(list.get(i).getId());
                formHelpDto.setFullName(list.get(i).getFullName());
                formHelpDto.setEmail(list.get(i).getEmail());
                formHelpDto.setPhone(list.get(i).getPhone());
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
//
//    @Override
//    public ResponseEntity<FormHelpDto> getFormHelpById(Long formId) {
//        FormHelp formHelp = formHelpRepository.findById(formId)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found form id"));
//
//        FormHelpDto formHelpDto = new FormHelpDto(
//                formHelp.getUser().getName(),
//                formHelp.getUser().getEmail(),
//                formHelp.getUser().getPhone(),
//                formHelp.getTitle(),
//                formHelp.getContents(),
//                formHelp.getFormStatus().getName(),
//                formHelp.getCreatedAt(),
//                formHelp.getFormImages()
//        );
//
//        return new ResponseEntity<>(formHelpDto, HttpStatus.OK);
//    }
//
    @Override
    public ResponseEntity<List<FormHelpDto>> getLatestFormHelpByUserId(String fullName) {
        List<FormHelpDto> listDto = new ArrayList<FormHelpDto>();
        List<FormHelp> list = formHelpRepository.findByUserId(fullName);
        for (int i = 0; i < list.size(); i++) {
            FormHelpDto formHelpDto = new FormHelpDto();

            formHelpDto.setId(list.get(i).getId());
            formHelpDto.setFullName(list.get(i).getFullName());
            formHelpDto.setEmail(list.get(i).getEmail());
            formHelpDto.setPhone(list.get(i).getPhone());
            formHelpDto.setTitle(list.get(i).getTitle());
            formHelpDto.setContents(list.get(i).getContents());
            formHelpDto.setCreatedAt(list.get(i).getCreatedAt());
            formHelpDto.setStatusName(list.get(i).getFormStatus().getName());
            formHelpDto.setFormImageList(list.get(i).getFormImages());

            listDto.add(formHelpDto);
        }
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FormHelpCreatorDto> addFormHelp(FormHelpCreatorDto formHelpCreatorDto) {
//        try {
            //convert dto to entity
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            FormHelp formHelpEntity = modelMapper.map(formHelpCreatorDto, FormHelp.class);

            formHelpEntity.setFullName(formHelpCreatorDto.getFullName());
            formHelpEntity.setEmail(formHelpCreatorDto.getEmail());
            formHelpEntity.setPhone(formHelpCreatorDto.getPhone());
            formHelpEntity.setTitle(formHelpCreatorDto.getTitle());
            formHelpEntity.setContents(formHelpCreatorDto.getContents());
            formStatusRepository.findById(formHelpCreatorDto.getStatusId()).map(formStatus -> {
                formHelpEntity.setFormStatus(formStatus);

                return formHelpEntity;
            }).orElseThrow(() -> new ResourceNotFoundException("Not found status with id"));

            //convert entity to dto
            FormHelpCreatorDto formHelpCreatorDto1 = modelMapper.map(formHelpRepository.save(formHelpEntity), FormHelpCreatorDto.class);

            return new ResponseEntity<>(formHelpCreatorDto1, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

//    @Override
//    public ResponseEntity<FormHelpCreatorDto> updateFormHelpById(Long formId, FormHelpCreatorDto formHelpCreatorDto) {
//        try{
//            //convert dto to entity
//            modelMapper.getConfiguration().setAmbiguityIgnored(true);
//            FormHelp formRequest = modelMapper.map(formHelpCreatorDto, FormHelp.class);
//
//            FormHelp formHelp = formHelpRepository.findById(formId)
//                    .orElseThrow(() -> new ResourceNotFoundException("Form id " + formId + "not found"));
//
//            formStatusRepository.findById(formHelpCreatorDto.getStatusId()).map(formStatus -> {
//                formRequest.setFormStatus(formStatus);
//
//                return formRequest;
//            }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));
//
//            formHelp.setId(formRequest.getId());
//            formHelp.setTitle(formRequest.getTitle());
//            formHelp.setContents(formRequest.getContents());
//
//            //convert entity to dto
//            FormHelpCreatorDto formHelpCreatorDto1 = modelMapper.map(formHelpRepository.save(formHelp), FormHelpCreatorDto.class);
//            return new ResponseEntity<>(formHelpCreatorDto1, HttpStatus.OK);
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Override
    public ResponseEntity<FormHelpCreatorDto> updateFormHelpStatus(Long formId, FormHelpCreatorDto formHelpCreatorDto) {
        //convert dto to entity
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        FormHelp formHelpEntity = modelMapper.map(formHelpCreatorDto, FormHelp.class);

        //check co ton tai formId hay khong
        FormHelp formHelp = formHelpRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form id not found"));

        if(formHelpCreatorDto.getStatusId() == 2){
            formStatusRepository.findById(formHelpCreatorDto.getStatusId()).map(status -> {
                formHelp.setFormStatus(status);

                return formHelp;
            }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

            formHelp.setId(formId);
            formHelp.setEmail(formHelpEntity.getEmail());
            formHelp.setPhone(formHelpEntity.getPhone());
            formHelp.setFullName(formHelpEntity.getFullName());
            formHelp.setTitle(formHelpEntity.getTitle());
            formHelp.setContents(formHelpEntity.getContents());
            formHelp.setFormImages(formHelpEntity.getFormImages());

            mailService.sendMail(formHelpCreatorDto.getFullName(), formHelpCreatorDto.getTitle(), "đã được duyệt", formHelpCreatorDto.getEmail());
        } else if(formHelpCreatorDto.getStatusId() == 3) {
            formStatusRepository.findById(formHelpCreatorDto.getStatusId()).map(status -> {
                formHelp.setFormStatus(status);

                return formHelp;
            }).orElseThrow(() -> new ResourceNotFoundException("Not status with id"));

            formHelp.setId(formId);
            formHelp.setEmail(formHelpEntity.getEmail());
            formHelp.setPhone(formHelpEntity.getPhone());
            formHelp.setFullName(formHelpEntity.getFullName());
            formHelp.setTitle(formHelpEntity.getTitle());
            formHelp.setContents(formHelpEntity.getContents());
            formHelp.setFormImages(formHelpEntity.getFormImages());

            mailService.sendMail(formHelpCreatorDto.getFullName(), formHelpCreatorDto.getTitle(), "đã bị từ chối", formHelpCreatorDto.getEmail());
        }

        FormHelpCreatorDto formHelpCreatorDto1 = modelMapper.map(formHelpRepository.save(formHelp), FormHelpCreatorDto.class);

        return new ResponseEntity<>(formHelpCreatorDto1, HttpStatus.CREATED);
    }
//
//    @Override
//    public ResponseEntity<Boolean> deleteFormHelpById(Long formId) {
//        try{
//            formHelpRepository.deleteById(formId);
//            return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
//        } catch (Exception e){
//            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
//        }
//    }

}
