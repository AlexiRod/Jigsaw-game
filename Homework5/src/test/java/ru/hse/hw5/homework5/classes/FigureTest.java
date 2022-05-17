package ru.hse.hw5.homework5.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {
    private static Figure figure;


    @Test
    void testGetters() {
        figure = FigureType.getPatternByType(FigureType.I_FIG).get(0);
        assertEquals(3, figure.getHeight());
        assertEquals(1, figure.getWidth());
    }

    @Test
    void testIsFilled() {
        figure = FigureType.getPatternByType(FigureType.L_FIG1).get(0);
        assertTrue(figure.isFilled(0,0));
        assertFalse(figure.isFilled(1,1));
    }

    @Test
    void testReplace() {
        figure = FigureType.getPatternByType(FigureType.L_FIG1).get(0);
        assertEquals(3, figure.getHeight());
        assertEquals(2, figure.getWidth());
        assertTrue(figure.isFilled(0,0));
        assertFalse(figure.isFilled(1,1));

        figure.replace(FigureType.getPatternByType(FigureType.L_FIG1).get(1));
        assertEquals(2, figure.getHeight());
        assertEquals(3, figure.getWidth());
        assertTrue(figure.isFilled(0,2));
        assertFalse(figure.isFilled(1,0));
    }
}