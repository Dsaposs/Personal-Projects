package com.ttrpg.helper.services.core.alien.dto;


import com.ttrpg.helper.services.EntityDtoMapper;
import com.ttrpg.helper.services.core.alien.entities.CharacterSheet;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private static final EntityDtoMapper<CharacterSheet,CharacterSheetDTO> entityDtoMapper = new EntityDtoMapper<>();

    public static CharacterSheetDTO convertEntityToDto(CharacterSheet entity) {
        return (CharacterSheetDTO) entityDtoMapper.convertToDto(entity, CharacterSheetDTO.class);
    }

    public static CharacterSheet convertDtoToEntity(CharacterSheetDTO dto) {
        return (CharacterSheet) entityDtoMapper.convertToEntity(dto, CharacterSheet.class);
    }
}