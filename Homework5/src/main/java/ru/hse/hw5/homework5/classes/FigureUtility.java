package ru.hse.hw5.homework5.classes;

import java.util.*;

public class FigureUtility {
    private final List<Figure> patterns = new ArrayList<>();
    private final Random rnd = new Random();

    public FigureUtility() {
        for (var type : FigureType.values()) {
            patterns.addAll(FigureType.getPatternByType(type));
        }
    }

    public FigureUtility(int s) {
        this();
        rnd.setSeed(s);
    }

    public Figure getFigure() {
        return patterns.get(rnd.nextInt(0, patterns.size()));
    }
}
