package com.ute.sunshinebackend.repository.Report;

import com.ute.sunshinebackend.entity.Contribution.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionReportRepository extends JpaRepository<Contribution, String> {
    @Query(value =
            "SELECT c.id, cm.amount_money, c.nickname, c.payment_type, c.created_at, c.receiver, p.id as 'projectId' " +
                    "FROM contributions as c, contribution_money as cm, users as u, projects as p " +
                    "WHERE c.id_contribution_money = cm.id and c.id_user = u.id and c.id_project = p.id " +
                    "ORDER BY c.created_at DESC ",
            nativeQuery = true)
    List listContributions();

    @Query(value =
            "SELECT c.id, cm.amount_money, c.nickname, c.payment_type, c.created_at, c.receiver, p.id as 'projectId' " +
                    "FROM contributions as c, contribution_money as cm, users as u, projects as p " +
                    "WHERE c.id_contribution_money = cm.id and c.id_user = u.id and c.id_project = p.id " +
                    "   and c.id_project = :projectId " +
                    "ORDER BY c.created_at DESC ",
            nativeQuery = true)
    List listContributionsByProjectId(Integer projectId);

    @Query(value =
            "SELECT c.id, cm.amount_money, c.nickname, c.payment_type, c.created_at, c.receiver, p.id as 'projectId' " +
                    "FROM contributions as c, contribution_money as cm, users as u, projects as p " +
                    "WHERE c.id_contribution_money = cm.id and c.id_user = u.id and c.id_project = p.id " +
                    "   and c.created_at between :fromDate and :toDate " +
                    "ORDER BY c.created_at DESC " ,
            nativeQuery = true)
    List listContributionsByDate(String fromDate, String toDate);

    @Query(value =
            "SELECT c.id, cm.amount_money, c.nickname, c.payment_type, c.created_at, c.receiver, p.id as 'projectId' " +
                    "FROM contributions as c, contribution_money as cm, users as u, projects as p " +
                    "WHERE c.id_contribution_money = cm.id and c.id_user = u.id and c.id_project = p.id " +
                    "   and c.id_project = :projectId " +
                    "   and c.created_at between :fromDate and :toDate " +
                    "ORDER BY c.created_at DESC " ,
            nativeQuery = true)
    List listContributionsByProjectIdByDate(Integer projectId, String fromDate, String toDate);
}
