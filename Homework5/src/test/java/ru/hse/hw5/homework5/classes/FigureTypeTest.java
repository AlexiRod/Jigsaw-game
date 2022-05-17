package ru.hse.hw5.homework5.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureTypeTest {

    @Test
    void getPatternByType() {
        Figure figure = FigureType.getPatternByType(FigureType.L_FIG1).get(3);
        assertEquals(2, figure.getHeight());
        assertEquals(3, figure.getWidth());

        figure = FigureType.getPatternByType(FigureType.ANG_FIG2).get(0);
        assertEquals(3, figure.getHeight());
        assertEquals(3, figure.getWidth());

        figure = FigureType.getPatternByType(FigureType.DOT_FIG).get(0);
        assertEquals(1, figure.getHeight());
        assertEquals(1, figure.getWidth());
    }
}