package com.ute.sunshinebackend.repository.Project;

import com.ute.sunshinebackend.entity.Project.ProjectPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProjectPaymentRepository extends JpaRepository<ProjectPayment, String>, JpaSpecificationExecutor<ProjectPayment> {

    @Query(value = "SELECT c.* FROM project_payment c, projects p WHERE c.id_project = p.id and c.id_project = :projectId", nativeQuery = true)
    List<ProjectPayment> findByProjectId(Long projectId);

    @Query(value =
            "SELECT c.id, cm.amount_money, c.nickname, c.payment_type, c.created_at, p1.id as 'projectId' " +
            "FROM contributions as c, contribution_money as cm, users as u1, projects as p1 " +
            "WHERE c.id_contribution_money = cm.id and c.id_user = u1.id and c.id_project = p1.id " +
            "UNION " +
            "SELECT pp.id, pp.amount_money, u2.name, pp.reason, pp.created_at, p2.id as 'projectId' " +
            "FROM project_payment as pp, users as u2, projects as p2 " +
            "WHERE pp.id_user = u2.id and pp.id_project = p2.id ",
            nativeQuery = true)
    List union();

    @Query(value =
            "SELECT c.id, cm.amount_money, c.nickname, c.payment_type, c.created_at, p1.id as 'projectId' " +
                    "FROM contributions as c, contribution_money as cm, users as u1, projects as p1 " +
                    "WHERE c.id_contribution_money = cm.id and c.id_user = u1.id and c.id_project = p1.id " +
                    "   and p1.id = :projectId1 " +
                    "UNION " +
                    "SELECT pp.id, pp.amount_money, u2.name, pp.reason, pp.created_at, p2.id as 'projectId' " +
                    "FROM project_payment as pp, users as u2, projects as p2 " +
                    "WHERE pp.id_user = u2.id and pp.id_project = p2.id " +
                    "   and p2.id = :projectId2 ",
            nativeQuery = true)
    List unionByProjectId(Integer projectId1, Integer projectId2);

    @Query(value =
            "SELECT c.id, cm.amount_money, c.nickname, c.payment_type, c.created_at, p1.id as 'projectId' " +
                    "FROM contributions as c, contribution_money as cm, users as u1, projects as p1 " +
                    "WHERE c.id_contribution_money = cm.id and c.id_user = u1.id and c.id_project = p1.id " +
                    "   and c.created_at between :fromDate1 and :toDate1 " +
                    "UNION " +
                    "SELECT pp.id, pp.amount_money, u2.name, pp.reason, pp.created_at, p2.id as 'projectId' " +
                    "FROM project_payment as pp, users as u2, projects as p2 " +
                    "WHERE pp.id_user = u2.id and pp.id_project = p2.id " +
                    "   and pp.created_at between :fromDate2 and :toDate2 ",
            nativeQuery = true)
    List unionByDate(String fromDate1, String toDate1, String fromDate2, String toDate2);

    //tong thu
    @Query(value =
            "SELECT SUM(cm.amount_money) " +
            "FROM contributions as c, contribution_money as cm " +
            "WHERE c.id_contribution_money = cm.id ",
            nativeQuery = true)
    BigDecimal sumMoneyByContribution();

    //tong thu theo du an
    @Query(value =
            "SELECT SUM(cm.amount_money) " +
            "FROM contributions as c, contribution_money as cm, projects as p " +
            "WHERE c.id_contribution_money = cm.id and c.id_project = p.id and p.id = :projectId ",
            nativeQuery = true)
    BigDecimal sumMoneyByContributionByProjectId(Integer projectId);

    //tong thu theo thoi gian
    @Query(value =
            "SELECT SUM(cm.amount_money) " +
                    "FROM contributions as c, contribution_money as cm " +
                    "WHERE c.id_contribution_money = cm.id and c.created_at between :fromDate and :toDate ",
            nativeQuery = true)
    BigDecimal sumMoneyByContributionByDate(String fromDate, String toDate);

    //tong chi
    @Query(value =
            "SELECT SUM(amount_money) FROM project_payment ", nativeQuery = true)
    BigDecimal sumMoneyByPayment();

    //tong chi theo du an
    @Query(value =
            "SELECT SUM(amount_money) " +
            "FROM project_payment as pp, projects as p " +
            "WHERE pp.id_project = p.id and p.id = :projectId ", nativeQuery = true)
    BigDecimal sumMoneyByPaymentIdProject(Integer projectId);

    //tong chi theo thoi gian
    @Query(value =
            "SELECT SUM(amount_money) " +
            "FROM project_payment " +
            "WHERE created_at between :fromDate and :toDate ", nativeQuery = true)
    BigDecimal sumMoneyByPaymentByDate(String fromDate, String toDate);
}
