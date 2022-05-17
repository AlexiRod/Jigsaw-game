package ru.hse.hw5.homework5.classes;

public class Figure {
    protected boolean[][] coords;
    protected int width, height;

    public Figure(boolean[][] figure) {
        this.coords = figure;
        width = figure[0].length;
        height = figure.length;
    }

    public void replace(Figure figure) {
        this.coords = figure.coords;
        width = coords[0].length;
        height = coords.length;
    }

    public boolean isFilled(int i, int j) {
        if (i < 0 || j < 0 || j > width - 1 || i > height - 1) {
            return true;
        }
        return coords[i][j];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
