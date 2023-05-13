package com.ute.sunshinebackend.repository.Form;

import com.ute.sunshinebackend.entity.Form.FormHelp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface FormHelpRepository extends JpaRepository<FormHelp, Long> {
    List<FormHelp> findByOrderByCreatedAtDesc();

    @Query(value = "SELECT f.* FROM form_help f, users u WHERE f.id_user = u.id and f.id_user = :userId", nativeQuery = true)
    List<FormHelp> findByUserId(Long userId);
}
