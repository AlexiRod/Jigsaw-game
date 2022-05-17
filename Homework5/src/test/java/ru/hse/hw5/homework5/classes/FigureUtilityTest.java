package ru.hse.hw5.homework5.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureUtilityTest {
    private static final FigureUtility util = new FigureUtility(1);

    @Test
    void testGetFigure() {
        Figure figure = util.getFigure();
        assertEquals(2, figure.getHeight());
        assertEquals(3, figure.getWidth());

        figure = util.getFigure();
        assertEquals(3, figure.getHeight());
        assertEquals(2, figure.getWidth());

        figure = util.getFigure();
        assertEquals(3, figure.getHeight());
        assertEquals(3, figure.getWidth());

        figure = util.getFigure();
        assertEquals(3, figure.getHeight());
        assertEquals(3, figure.getWidth());

    }
}