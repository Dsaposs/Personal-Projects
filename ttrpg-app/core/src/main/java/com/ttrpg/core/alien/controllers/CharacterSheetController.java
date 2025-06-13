package com.ttrpg.core.alien.controllers;

import com.ttrpg.core.alien.repositories.dto.CharacterSheetDTO;
import com.ttrpg.core.alien.repositories.entities.CharacterSheet;
import com.ttrpg.core.alien.services.CharacterSheetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class CharacterSheetController {
    private final CharacterSheetService characterSheetService;
    private final ModelMapper modelMapper;

    @Autowired
    public CharacterSheetController(CharacterSheetService characterSheetService, ModelMapper modelMapper) {
        this.characterSheetService = characterSheetService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/character/{userId}/{characterId}")
    public ResponseEntity<CharacterSheetDTO> getCharacterSheet(@PathVariable("userId") String userId, @PathVariable("characterId") String characterId) {
        CharacterSheet cs = characterSheetService.findCharacterSheet(Integer.valueOf(userId), Integer.valueOf(characterId));
        return new ResponseEntity<>(convertToDto(cs), HttpStatus.OK);
    }

    @PostMapping("/character/{userId}")
    public ResponseEntity<String> createCharacterSheet(@PathVariable("userId") String userId, @RequestBody CharacterSheet characterSheet) {
        characterSheet.setUserId(Integer.valueOf(userId));
        characterSheetService.save(characterSheet);
        return new ResponseEntity<>("New Character Sheet Created", HttpStatus.OK);
    }

    @GetMapping("/characters")
    public @ResponseBody Iterable<CharacterSheet> getAllCharacterSheets() {
        return characterSheetService.findAll();
    }

    private CharacterSheetDTO convertToDto(CharacterSheet cs) {
        return modelMapper.map(cs, CharacterSheetDTO.class);
    }

    private CharacterSheet convertToEntity(CharacterSheetDTO dto) {
        return modelMapper.map(dto, CharacterSheet.class);
    }
}