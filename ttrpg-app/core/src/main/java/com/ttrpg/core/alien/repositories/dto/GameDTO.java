package com.ttrpg.core.alien.repositories.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class GameDTO {
    private Integer gameId;
    private Integer ownerId;
    private List<Integer> playerIds;
}