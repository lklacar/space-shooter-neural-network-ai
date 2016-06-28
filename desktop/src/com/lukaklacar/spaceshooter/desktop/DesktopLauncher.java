package com.lukaklacar.spaceshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lukaklacar.spaceshooter.SpaceShooter;
import com.lukaklacar.spaceshooter.neuralnetwork.NeuralNetwork;
import com.lukaklacar.spaceshooter.neuralnetwork.SigmoidActivationFunction;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 1024;
        config.width = 768;

        new LwjglApplication(new SpaceShooter(), config);


    }
}
