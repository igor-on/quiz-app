package com.in.quiz.frontend;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static com.in.quiz.frontend.Difficulty.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DifficultyTest {

    @Test
    void calculateNextDifficulty_ShouldReturnNullOnNullInput() {
        EnumSet<Difficulty> given = null;
        Difficulty actual = calculateNextDifficulty(given);
        assertNull(actual);
    }

    @Test
    void calculateNextDifficulty_ShouldReturnNullOnNoneInput() {
        EnumSet<Difficulty> given = EnumSet.noneOf(Difficulty.class);
        Difficulty actual = calculateNextDifficulty(given);
        assertNull(actual);
    }

    @Test
    void calculateNextDifficulty_ShouldReturnMediumOnEasyInput() {
        EnumSet<Difficulty> given = EnumSet.of(EASY);
        Difficulty actual = calculateNextDifficulty(given);
        assertEquals(MEDIUM, actual);
    }

    @Test
    void calculateNextDifficulty_ShouldReturnHardOnMediumInput() {
        EnumSet<Difficulty> given = EnumSet.of(MEDIUM);
        Difficulty actual = calculateNextDifficulty(given);
        assertEquals(HARD, actual);
    }

    @Test
    void calculateNextDifficulty_ShouldReturnMediumOnHardInput() {
        EnumSet<Difficulty> given = EnumSet.of(HARD);
        Difficulty actual = calculateNextDifficulty(given);
        assertEquals(MEDIUM, actual);
    }

    @Test
    void calculateNextDifficulty_ShouldReturnHardOnEasy_MediumInput() {
        EnumSet<Difficulty> given = EnumSet.of(EASY, MEDIUM);
        Difficulty actual = calculateNextDifficulty(given);
        assertEquals(HARD, actual);
    }

    @Test
    void calculateNextDifficulty_ShouldReturnEasyOnMedium_HardInput() {
        EnumSet<Difficulty> given = EnumSet.of(MEDIUM, HARD);
        Difficulty actual = calculateNextDifficulty(given);
        assertEquals(EASY, actual);
    }

    @Test
    void calculateNextDifficulty_ShouldReturnMediumOnHard_EasyInput() {
        EnumSet<Difficulty> given = EnumSet.of(HARD, EASY);
        Difficulty actual = calculateNextDifficulty(given);
        assertEquals(MEDIUM, actual);
    }

    @Test
    void calculateNextDifficulty_ShouldReturnNullOnAllInput() {
        EnumSet<Difficulty> given = EnumSet.of(EASY, MEDIUM, HARD);
        Difficulty actual = calculateNextDifficulty(given);
        assertNull(actual);
    }

}
