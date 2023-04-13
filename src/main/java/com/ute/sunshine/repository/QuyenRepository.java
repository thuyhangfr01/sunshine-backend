package com.ute.sunshine.repository;

import com.ute.sunshine.model.Quyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyenRepository extends JpaRepository<Quyen, Integer> {

}
