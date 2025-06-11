package com.ttrpg.services;

import com.ttrpg.entities.CharacterSheet;
import com.ttrpg.repositories.CharacterSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterSheetService {
    @Autowired
    private CharacterSheetRepository characterSheetRepository;

    public CharacterSheet findCharacterSheet(Integer userId, Integer characterId) {
        return characterSheetRepository.findByUserIdAndCharacterId(userId, characterId);
    }

    public List<CharacterSheet> findAllForUser(Integer userId) {
        return characterSheetRepository.findAllByUserId(userId);
    }

    public List<CharacterSheet> findAll() {
        return characterSheetRepository.findAll();
    }

    public void save(CharacterSheet characterSheet) {
        characterSheetRepository.save(characterSheet);
    }
}