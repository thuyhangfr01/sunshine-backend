package com.ute.sunshinebackend.service.Project;

import com.ute.sunshinebackend.entity.Project.ProjectMoney;
import org.springframework.http.ResponseEntity;
import java.util.*;
public interface ProjectMoneyService {
    //get money by project id
    public ResponseEntity<List<ProjectMoney>> getMoney(Long projectId);

    public ResponseEntity<Optional<ProjectMoney>> getMoneyById(Long id);

    //add money by project id
    public ResponseEntity<ProjectMoney> addMoney(Long projectId, ProjectMoney projectMoney);

    //update money by project id
    public ResponseEntity<ProjectMoney> updateMoney(Long moneyId, ProjectMoney projectMoney);
}
