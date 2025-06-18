package com.ttrpg.core.game;

public interface GameCharacter extends GameObject {
    public CharacterStat getStat(String name);
//    public RollResult makeStatCheck(String statName, int target, boolean hasAdvantage);
}