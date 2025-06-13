package com.ttrpg.core.alien.repositories;

import com.ttrpg.alien.repositories.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Game getGameByGameId(Integer id);
    List<Game> getGamesByOwnerId(Integer id);
}