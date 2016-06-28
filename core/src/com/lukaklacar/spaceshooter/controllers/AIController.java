package com.lukaklacar.spaceshooter.controllers;

import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.math.Matrix;
import com.lukaklacar.spaceshooter.neuralnetwork.NeuralNetwork;
import com.lukaklacar.spaceshooter.neuralnetwork.SigmoidActivationFunction;
import com.lukaklacar.spaceshooter.util.Util;

import java.util.ArrayList;

import static com.lukaklacar.spaceshooter.Config.PLAYER_SPEED;

/**
 * Created by Luka on 6/7/2016.
 */
public class AIController extends Controller {


    private NeuralNetwork nn;

    public AIController() {
        nn = new NeuralNetwork(2, new int[]{200, 200, 200, 200}, 5, new SigmoidActivationFunction());
    }


    @Override
    public void update(SpaceshipEntity entity) {
        SpaceshipEntity closest = findClosestEntity(entity, Util.getGameScreen().getEntities());
        double distance = calcDistance(entity, closest);
        double angle = calcAngle(entity, closest);

        Matrix m = nn.calculate(distance, angle);


        entity.getVelocity().set((float) (m.getData()[0][0] - 0.5) * PLAYER_SPEED, (float) (m.getData()[1][0] - 0.5) * PLAYER_SPEED);
    }


    private SpaceshipEntity findClosestEntity(SpaceshipEntity me, ArrayList<SpaceshipEntity> allEntities) {
        SpaceshipEntity closest = allEntities.get(0);
        double closestDistance = Double.MAX_VALUE;


        for (SpaceshipEntity entity : allEntities) {
            if (entity == me)
                continue;


            double distance = calcDistance(me, entity);

            if (distance < closestDistance) {
                closest = entity;
                closestDistance = distance;
            }

        }

        return closest;
    }


    private double calcDistance(SpaceshipEntity e1, SpaceshipEntity e2) {
        return Math.sqrt(Math.pow(e1.getPosition().x - e2.getPosition().x, 2) + Math.pow(e1.getPosition().y - e2.getPosition().y, 2));
    }


    private double calcAngle(SpaceshipEntity e1, SpaceshipEntity e2) {
        double deltaY = e1.getPosition().y - e2.getPosition().y;
        double deltaX = e1.getPosition().x - e2.getPosition().x;


        return Math.atan2(deltaY, deltaX) * 180 / Math.PI;
    }

}
