package com.ttrpg.core.game.events;

import com.ttrpg.core.game.GameCharacter;
import com.ttrpg.core.game.GameObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActionEvent implements Event {
    private GameCharacter subject;
    private GameObject object;
    private Action verb;
}