package com.ttrpg.core.game;

import com.ttrpg.core.game.events.ActionEvent;
import com.ttrpg.core.game.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public abstract class PlayerCharacter implements GameCharacter {
    private final int id;
    private final String name;
    private Map<String, CharacterStat> stats;

    @Override
    public CharacterStat getStat(String name) {
        CharacterStat stat = stats.get(name);
        if (stat == null) throw new IllegalArgumentException("Stat " + name + " not found");
        return stat;
    }

    public ActionEvent takeAction(GameObject object, Event.Action action) {
        return new ActionEvent(this, object, action);
    }
}