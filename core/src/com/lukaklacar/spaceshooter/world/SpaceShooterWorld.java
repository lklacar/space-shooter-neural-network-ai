package com.lukaklacar.spaceshooter.world;

import com.lukaklacar.spaceshooter.ShootListener;

/**
 * Created by Luka on 7/8/2016.
 */
public class SpaceShooterWorld extends AbstractWorld {

    public static SpaceShooterWorld instance;

    private SpaceShooterWorld() {
        addListener(new ShootListener());
    }

    public static SpaceShooterWorld getInstance() {
        if (instance == null)
            instance = new SpaceShooterWorld();

        return instance;
    }

}
