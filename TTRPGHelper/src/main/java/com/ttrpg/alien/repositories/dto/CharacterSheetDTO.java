package com.ttrpg.alien.repositories.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CharacterSheetDTO {
    private Integer characterId;
    private Integer userId;
    private String characterName;
    private Integer strength;
    private Integer wits;
    private Integer empathy;
    private Integer agility;
}
