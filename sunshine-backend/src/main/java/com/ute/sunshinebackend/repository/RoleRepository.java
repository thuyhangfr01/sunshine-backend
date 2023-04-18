package com.ute.sunshinebackend.repository;

import com.ute.sunshinebackend.entity.ERole;
import com.ute.sunshinebackend.entity.Role;
import com.ute.sunshinebackend.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
