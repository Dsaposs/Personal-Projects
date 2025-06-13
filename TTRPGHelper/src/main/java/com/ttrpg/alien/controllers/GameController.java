package com.ttrpg.alien.controllers;

import com.ttrpg.alien.repositories.dto.GameDTO;
import com.ttrpg.alien.repositories.entities.Game;
import com.ttrpg.alien.services.GameService;

import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    private final GameService gameService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameController(GameService gameService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<GameDTO> getGameByUser(@PathParam("id") Integer id) {
        GameDTO gameDTO = convertToDto(gameService.getGameById(id));
        return new ResponseEntity<>(gameDTO, HttpStatus.OK);
    }

    @PostMapping("/game/create")
    public ResponseEntity<String> createGame(@RequestBody GameDTO gameInfo) {
        Game g = convertToEntity(gameInfo);
        gameService.save(g);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Game convertToEntity(GameDTO gameInfo) {
        return modelMapper.map(gameInfo, Game.class);
    }

    private GameDTO convertToDto(Game g) {
        return modelMapper.map(g, GameDTO.class);
    }
}