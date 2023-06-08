package com.ute.sunshinebackend.repository.Form;

import com.ute.sunshinebackend.entity.Form.FormImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormImageRepository extends JpaRepository<FormImage, Long> {

}
