package com.ttrpg.auth.services;

import com.ttrpg.alien.repositories.GameRepository;
import com.ttrpg.alien.repositories.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game getGameById(Integer id) {
        return gameRepository.getGameByGameId(id);
    }

    public void save(Game g) {
        gameRepository.save(g);
    }
}