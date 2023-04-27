package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectMoney;
import org.springframework.http.ResponseEntity;
import java.util.List;
public interface ProjectMoneyService {
    //get money by project id
    public ResponseEntity<List<ProjectMoney>> getMoney(Long projectId);

    //add money by project id
    public ResponseEntity<ProjectMoney> addMoney(Long projectId, ProjectMoney projectMoney);

    //update money by project id
    public ResponseEntity<ProjectMoney> updateMoney(Long id, ProjectMoney projectMoney);
}
