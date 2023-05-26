package com.ute.sunshinebackend.repository.Form;

import com.ute.sunshinebackend.entity.Form.FormHelp;
import com.ute.sunshinebackend.entity.Form.FormVolunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormVolunteerRepository extends JpaRepository<FormVolunteer, Long> {
    List<FormVolunteer> findByOrderByCreatedAtDesc();

    @Query(value = "SELECT f.* FROM form_volunteers f, projects p WHERE f.id_project = p.id and f.id_project = :projectId ORDER BY created_at DESC", nativeQuery = true)
    List<FormVolunteer> findByProjectId(Long projectId);
}
