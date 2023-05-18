package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.entity.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsById(Long idProject);
    Page<Project> findAll(Pageable pageable);
    Page<Project> findByNameContaining(String name, Pageable pageable);
    Page<Project> findByProjectTypeId(Long idType, Pageable pageable);
    Page<Project> findByProjectStatusId(Long idStatus, Pageable pageable);
    Page<Project> findByOrderByCreatedAtDesc(Pageable pageable);
    List<Project> findByNameContainingOrderByCreatedAtDesc(String name);
    List<Project> findByOrderByCreatedAtDesc();
    @Transactional
    void deleteById(Long id);

    //tong tien dong gop cua tung du an
    @Query(value = "select sum(amount_money) " +
            "from contributions as a, contribution_money as b, projects as c " +
            "where a.id_contribution_money = b.id and a.id_project = c.id and b.id_status = 3 and c.id = :projectId " +
            "group by c.id", nativeQuery = true)
    Long getTotalMoneyByProjectId(long projectId);



}
