package com.ttrpg.core.game;

import lombok.Getter;

import java.util.Random;

@Getter
public class DiceRoller {
    private final int numberOfSides;
    public record RollResult(int totalValue, int successes, int criticalFailures){};

    public DiceRoller(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public RollResult roll(int numberOfDice) {
        int value = 0, successes = 0, criticalFailures = 0;
        Random rand = new Random();
        for (int i = 0; i < numberOfSides; i++) {
            int rollVal = rand.nextInt(numberOfSides) + 1;
            if (rollVal == 1)
                criticalFailures += 1;
            else if (rollVal == numberOfSides)
                successes += 1;
            value += rollVal;
        }
        return new RollResult(value, successes, criticalFailures);
    }
}