package com.lukaklacar.spaceshooter.util;

import com.badlogic.gdx.Gdx;
import com.lukaklacar.spaceshooter.SpaceShooter;
import com.lukaklacar.spaceshooter.graphics.screens.GameScreen;

/**
 * Created by Luka on 6/7/2016.
 */
public class Util {


    public static SpaceShooter getGameInstance() {
        return ((SpaceShooter) Gdx.app.getApplicationListener());
    }


    public static GameScreen getGameScreen() {
        return (GameScreen) getGameInstance().getScreen();


    }
}
