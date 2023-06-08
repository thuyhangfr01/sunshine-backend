package com.ute.sunshinebackend.repository.Form;

import com.ute.sunshinebackend.entity.Form.FormStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormStatusRepository extends JpaRepository<FormStatus, Long> {
}
