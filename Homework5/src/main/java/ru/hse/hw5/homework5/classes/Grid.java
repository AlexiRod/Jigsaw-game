package ru.hse.hw5.homework5.classes;

public class Grid extends Figure {

    public Grid(int size) {
        super(new boolean[size][size]);
    }

    public void resize(int size) {
        coords = new boolean[size][size];
        width = size;
        height = size;
    }

    public boolean isFilled(Figure figure, int x, int y) {
        if (x < 0 || y < 0 || figure.getWidth() + x > width || figure.getHeight() + y > height) {
            return true;
        }

        for (int j = y; j < y + figure.getHeight(); j++) {
            for (int i = x; i < x + figure.getWidth(); i++) {
                if (coords[j][i] && figure.isFilled(j - y, i - x )) {
                    return true;
                }
            }
        }
        return false;
    }

    public void placeFigure(Figure figure, int x, int y) {
        if (x < 0 || y < 0 || figure.getWidth() + x > width || figure.getHeight() + y > height) {
            return;
        }

        for (int i = y; i < y + figure.getHeight(); i++) {
            for (int j = x; j < x + figure.getWidth(); j++) {
                coords[i][j] = coords[i][j] || figure.isFilled(i - y, j - x);
            }
        }
    }
}
