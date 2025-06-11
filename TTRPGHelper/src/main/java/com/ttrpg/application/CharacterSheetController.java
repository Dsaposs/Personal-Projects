package com.ttrpg.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class CharacterSheetController {
    @Autowired
    private CharacterSheetService characterSheetService;

    @GetMapping("/character/{userId}/{characterId}")
    public @ResponseBody String getCharacterSheet(@PathVariable("userId") String userId, @PathVariable("characterId") String characterId) {
        return characterSheetService.findCharacterSheet(Integer.valueOf(userId), Integer.valueOf(characterId)).toString();
    }

    @PostMapping("/character/{id}")
    public @ResponseBody Integer createCharacterSheet(@PathVariable("id") String userId, @RequestBody CharacterSheet characterSheet) {
        characterSheet.setUserId(Integer.valueOf(userId));
        characterSheetService.save(characterSheet);
        return characterSheet.getCharacterId();
    }

    @GetMapping("/characters")
    public @ResponseBody Iterable<CharacterSheet> getAllCharacterSheets() {
        return characterSheetService.findAll();
    }
}