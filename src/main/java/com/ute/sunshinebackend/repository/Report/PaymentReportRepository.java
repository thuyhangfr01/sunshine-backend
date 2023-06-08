package com.ute.sunshinebackend.repository.Report;

import com.ute.sunshinebackend.entity.Project.ProjectPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentReportRepository extends JpaRepository<ProjectPayment, String> {
    @Query(value =
            "SELECT pp.id, pp.amount_money, u.name, pp.reason, pp.created_at, pp.receiver, p.id as 'projectId' " +
                    "FROM project_payment as pp, users as u, projects as p " +
                    "WHERE pp.id_user = u.id and pp.id_project = p.id " +
                    "ORDER BY pp.created_at DESC ",
            nativeQuery = true)
    List listPayments();

    @Query(value =
            "SELECT pp.id, pp.amount_money, u.name, pp.reason, pp.created_at, pp.receiver, p.id as 'projectId' " +
                    "FROM project_payment as pp, users as u, projects as p " +
                    "WHERE pp.id_user = u.id and pp.id_project = p.id " +
                    " and pp.id_project = :projectId " +
                    "ORDER BY pp.created_at DESC ",
            nativeQuery = true)
    List listPaymentsByProjectId(Integer projectId);

    @Query(value =
            "SELECT pp.id, pp.amount_money, u.name, pp.reason, pp.created_at, pp.receiver, p.id as 'projectId' " +
                    "FROM project_payment as pp, users as u, projects as p " +
                    "WHERE pp.id_user = u.id and pp.id_project = p.id " +
                    "   and pp.created_at between :fromDate and :toDate " +
                    "ORDER BY pp.created_at DESC " ,
            nativeQuery = true)
    List listPaymentsByDate(String fromDate, String toDate);

    @Query(value =
            "SELECT pp.id, pp.amount_money, u.name, pp.reason, pp.created_at, pp.receiver, p.id as 'projectId' " +
                    "FROM project_payment as pp, users as u, projects as p " +
                    "WHERE pp.id_user = u.id and pp.id_project = p.id " +
                    "   and pp.id_project = :projectId " +
                    "   and pp.created_at between :fromDate and :toDate " +
                    "ORDER BY pp.created_at DESC " ,
            nativeQuery = true)
    List listPaymentsByProjectIdByDate(Integer projectId, String fromDate, String toDate);
}
