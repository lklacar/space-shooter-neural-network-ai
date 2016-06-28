package com.lukaklacar.spaceshooter.util;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class MathUtil {

    private static Random rand = new Random();

    public static int randInt(int min, int max) {

        return rand.nextInt((max - min) + 1) + min;
    }

    public static double randRange(double min, double max) {

        return min + (max - min) * rand.nextDouble();
    }


    public static Vector2 randVector(int min, int max) {
        return new Vector2(randInt(min, max), randInt(min, max));
    }
}
