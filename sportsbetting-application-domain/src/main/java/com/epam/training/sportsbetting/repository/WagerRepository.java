package com.epam.training.sportsbetting.repository;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagerRepository extends JpaRepository<Wager,Long> {
    List<Wager> findByPlayer(Player player);
}
