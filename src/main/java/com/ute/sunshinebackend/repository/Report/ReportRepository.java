package com.ute.sunshinebackend.repository.Report;


import com.ute.sunshinebackend.entity.Report;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByOrderByCreatedAtDesc();

    @Query(value = "SELECT r.* FROM reports r WHERE r.title LIKE %:title% ORDER BY r.created_at DESC", nativeQuery = true)
    List<Report> findByTitle(@Param("title") String title);
}
