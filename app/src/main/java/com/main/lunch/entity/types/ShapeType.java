package com.main.lunch.entity.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ShapeType {
    ROCK("rock", 1), PAPER("paper", 2), SCISSORS("scissors", 3);

    private final String name;
    private final int value;

    ShapeType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static ShapeType get(String name) {

        for (ShapeType type : values()) {

            if (type.getName().equalsIgnoreCase(name)) {

                return type;
            }
        }
        return null;
    }

    public static boolean exist(String name) {
        for (ShapeType type : values()) {

            if (type.getName().equalsIgnoreCase(name)) {

                return true;
            }
        }
        return false;
    }

    private static final List<ShapeType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ShapeType randomShape() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
