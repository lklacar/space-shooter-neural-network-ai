package com.lukaklacar.spaceshooter.util;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;

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

    public static double getDistance(Vector2 e1, Vector2 e2) {
        return Math.sqrt(Math.pow(e1.x - e2.x, 2) + Math.pow(e1.y - e2.y, 2));
    }

    public static double getAngle(Vector2 e1, Vector2 e2) {
        double deltaY = e1.y - e2.y;
        double deltaX = e1.x - e2.x;


        return Math.atan2(deltaY, deltaX) * 180 / Math.PI;
    }
}
