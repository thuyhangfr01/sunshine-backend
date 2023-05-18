package com.ute.sunshinebackend.repository.User;

import com.ute.sunshinebackend.entity.User.RefreshToken;
import java.util.Optional;

import com.ute.sunshinebackend.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
