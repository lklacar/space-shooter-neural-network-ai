package com.lukaklacar.spaceshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lukaklacar.spaceshooter.AbstractGame;
import com.lukaklacar.spaceshooter.SpaceShooterGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 1024;
        config.width = 768;

        new LwjglApplication(new SpaceShooterGame(), config);


    }
}
