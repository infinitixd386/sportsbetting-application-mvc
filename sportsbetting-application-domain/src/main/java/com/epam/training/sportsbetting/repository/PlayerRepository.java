package com.epam.training.sportsbetting.repository;

import com.epam.training.sportsbetting.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findByEmail(String email);
    Optional<Player> findByEmailAndPassword(String email, String password);
}
