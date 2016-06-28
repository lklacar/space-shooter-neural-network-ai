package com.lukaklacar.spaceshooter;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.controllers.AIController;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.util.MathUtil;

import java.util.ArrayList;

/**
 * Created by Luka on 6/28/2016.
 */
public class Main {
    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            SpaceshipEntity temp = new SpaceshipEntity(MathUtil.randVector(0, 1000), new Vector2(100, 100), new AIController());
            World.getInstance().add(temp);
        }


    }
}
