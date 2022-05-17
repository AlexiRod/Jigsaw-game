package ru.hse.hw5.homework5.classes;

import javafx.util.Pair;

import java.util.*;

public enum FigureType {
    L_FIG1,
    L_FIG2,
    Z_FIG1,
    Z_FIG2,
    T_FIG1,
    T_FIG2,
    ANG_FIG1,
    ANG_FIG2,
    DOT_FIG,
    I_FIG;

    public static List<Figure> getPatternByType(FigureType type) {
        switch (type) {
            case L_FIG1 -> {
                boolean[][] arr = new boolean[3][2];
                arr[0][0] = true;
                arr[0][1] = true;
                arr[1][0] = true;
                arr[2][0] = true;
                return rotate4(arr);
            }
            case L_FIG2 -> {
                boolean[][] arr = new boolean[3][2];
                arr[0][0] = true;
                arr[0][1] = true;
                arr[1][1] = true;
                arr[2][1] = true;
                return rotate4(arr);
            }
            case Z_FIG1 -> {
                boolean[][] arr = new boolean[3][2];
                arr[0][0] = true;
                arr[1][0] = true;
                arr[1][1] = true;
                arr[2][1] = true;
                return rotate2(arr);
            }
            case Z_FIG2 -> {
                boolean[][] arr = new boolean[3][2];
                arr[0][1] = true;
                arr[1][0] = true;
                arr[1][1] = true;
                arr[2][0] = true;
                return rotate2(arr);
            }
            case T_FIG1 -> {
                boolean[][] arr = new boolean[3][2];
                arr[0][0] = true;
                arr[1][0] = true;
                arr[1][1] = true;
                arr[2][0] = true;
                return rotate4(arr);
            }
            case T_FIG2 -> {
                boolean[][] arr = new boolean[3][3];
                arr[0][2] = true;
                arr[0][1] = true;
                arr[0][0] = true;
                arr[1][0] = true;
                arr[2][0] = true;
                return rotate4(arr);
            }
            case ANG_FIG1 -> {
                boolean[][] arr = new boolean[2][2];
                arr[0][0] = true;
                arr[1][0] = true;
                arr[0][1] = true;
                return rotate4(arr);
            }
            case ANG_FIG2 -> {
                boolean[][] arr = new boolean[3][3];
                arr[0][0] = true;
                arr[1][0] = true;
                arr[2][0] = true;
                arr[1][1] = true;
                arr[1][2] = true;
                return rotate4(arr);
            }
            case I_FIG -> {
                boolean[][] arr = new boolean[3][1];
                arr[0][0] = true;
                arr[1][0] = true;
                arr[2][0] = true;
                return rotate2(arr);
            }
            case DOT_FIG -> {
                return new ArrayList<>(List.of(new Figure(new boolean[][]{{true}})));
            }
            default -> {
                return new ArrayList<>(List.of(new Figure(new boolean[][]{{}})));
            }
        }
    }

    private static Figure rotate(Figure figure) {
        int height = figure.getHeight();
        int width = figure.getWidth();
        boolean[][] arr = new boolean[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                arr[j][height - 1 - i] = figure.isFilled(i, j);
            }
        }
        return new Figure(arr);
    }

    private static List<Figure> rotate2(boolean[][] arr) {
        ArrayList<Figure> res = new ArrayList<>();
        res.add(new Figure(arr));
        res.add(rotate(res.get(0)));
        return res;
    }

    private static List<Figure> rotate4(boolean[][] arr) {
        ArrayList<Figure> res = new ArrayList<>();
        res.add(new Figure(arr));
        res.add(rotate(res.get(0)));
        res.add(rotate(res.get(1)));
        res.add(rotate(res.get(2)));
        return res;
    }
}
