package com.ttrpg.core.game.events;

import com.ttrpg.core.game.GameCharacter;
import com.ttrpg.core.game.GameObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultEvent implements Event {
    private GameObject subject;
    private Reaction verb;
}
