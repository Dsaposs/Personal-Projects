package com.ttrpg.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterSheetRepository  extends JpaRepository<CharacterSheet,Integer> {
    List<CharacterSheet> findAllByUserId(Integer userId);

    CharacterSheet findByUserIdAndCharacterId(Integer userId, Integer characterId);
}