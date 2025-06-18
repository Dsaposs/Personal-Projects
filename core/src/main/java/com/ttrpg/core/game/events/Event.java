package com.ttrpg.core.game.events;

public interface Event {
    record Action(String name, EventType type, String skillName){}

    record Reaction(String name, StateChanged type, Object newValue){}

    enum EventType{
        DAMAGE,
        HEAL,
        MOVE;
    }

    enum StateChanged {
        HEALTH,
        POSITON,
        STANCE;
    }
}