package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.entity.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsById(Long idProject);
    Page<Project> findAll(Pageable pageable);
    Page<Project> findByNameContainingOrderByCreatedAtDesc(String name, Pageable pageable);
    Page<Project> findByProjectTypeIdOrderByCreatedAtDesc(Long idType, Pageable pageable);
    Page<Project> findByProjectStatusIdOrderByCreatedAtDesc(Long idStatus, Pageable pageable);
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
    BigDecimal getTotalMoneyByProjectId(long projectId);

    @Query(value =
            "SELECT p.id, p.name, p.details, pm.min_money, p.created_at " +
            "FROM projects as p, project_money as pm " +
            "WHERE p.id = pm.id_project", nativeQuery = true)
    List getAllProject();

    @Query(value =
            "SELECT pi.name " +
                    "FROM projects as p, project_images as pi " +
                    "WHERE p.id = pi.id_project and pi.id = :projectId", nativeQuery = true)
    List getImagesByProjectId(Integer projectId);

}
