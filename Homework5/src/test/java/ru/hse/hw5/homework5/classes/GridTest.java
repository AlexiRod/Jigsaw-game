package ru.hse.hw5.homework5.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private static final Grid grid = new Grid(4);

    @Test
    void testResize() {
        grid.resize(5);
        assertEquals(5, grid.getWidth());
        assertEquals(5, grid.getHeight());
    }

    @Test
    void testIsFilled() {
        FigureUtility util = new FigureUtility(1);
        Figure figure = util.getFigure();
        grid.resize(4);
        assertFalse(grid.isFilled(figure, 0, 1));
        assertTrue(grid.isFilled(figure, 0, -4));
        assertTrue(grid.isFilled(figure, 34, 3));
    }

    @Test
    void testPlaceFigure() {
        FigureUtility util = new FigureUtility(1);
        grid.resize(5);

        grid.placeFigure(util.getFigure(), 0,0);
        assertTrue(grid.isFilled(0, 0));

        grid.placeFigure(util.getFigure(), 2,2);
        assertTrue(grid.isFilled(2, 2));
        assertFalse(grid.isFilled(4, 4));
    }
}