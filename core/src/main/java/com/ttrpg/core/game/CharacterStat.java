package com.ttrpg.core.game;

import lombok.*;

@Data
@Getter
@Setter
public class CharacterStat {

    private final String name;
    private final String description;
    private Integer value;
    private Integer modifier;
    private final CharacterStat parent;

    public CharacterStat(String name, String description, Integer value) {
        this.parent = null;
        this.value = value;
        this.description = description;
        this.name = name;
        this.modifier = 0;
    }

    public CharacterStat(String name, String description, Integer value, CharacterStat parent) {
        this.parent = parent;
        this.value = value;
        this.description = description;
        this.name = name;
        this.modifier = 0;
    }

    public Integer getValue() {
        int returnVal = value != null ? value : (parent != null ? parent.getValue() : -1);
        return returnVal > 0 ? returnVal + modifier : returnVal;
    }
}