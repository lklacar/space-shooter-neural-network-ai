package com.lukaklacar.spaceshooter;

/**
 * Created by Luka on 6/28/2016.
 */
public class Main {
    public static void main(String[] args) {


        World.getInstance().populate(1000);

        for (int i = 0; i < 1000; i++) {
            World.getInstance().simulateOneIteration();
            System.out.println("Iteration");
        }


    }
}
