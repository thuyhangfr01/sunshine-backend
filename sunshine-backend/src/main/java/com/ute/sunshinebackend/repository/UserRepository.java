package com.ute.sunshinebackend.repository;

import java.util.*;
import com.ute.sunshinebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Modifying
    @Query(value = "update roles_users ru set ru.id_role= :idRole where ru.id_user= :idUser", nativeQuery = true)
    Boolean findRoleIdByUserId(long idRole, long idUser);
}
