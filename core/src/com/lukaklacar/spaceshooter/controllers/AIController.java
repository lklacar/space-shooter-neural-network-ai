package com.lukaklacar.spaceshooter.controllers;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.Bullet;
import com.lukaklacar.spaceshooter.Config;
import com.lukaklacar.spaceshooter.SpaceShooter;
import com.lukaklacar.spaceshooter.World;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.math.Matrix;
import com.lukaklacar.spaceshooter.neuralnetwork.NeuralNetwork;
import com.lukaklacar.spaceshooter.neuralnetwork.SigmoidActivationFunction;

import java.util.ArrayList;

import static com.lukaklacar.spaceshooter.Config.PLAYER_SPEED;

/**
 * Created by Luka on 6/7/2016.
 */
public class AIController extends Controller {


    public NeuralNetwork getNn() {
        return nn;
    }

    public void setNn(NeuralNetwork nn) {
        this.nn = nn;
    }

    private NeuralNetwork nn;
    private int iterationShoot;

    public AIController() {
        nn = new NeuralNetwork(6, new int[]{200, 200, 200}, 5, new SigmoidActivationFunction());
        iterationShoot = 0;
    }


    public AIController(NeuralNetwork nn) {
        this.nn = nn;
        iterationShoot = 0;
    }

    @Override
    public void update(SpaceshipEntity entity) {
        SpaceshipEntity closest = findClosestEntity(entity, World.getInstance().getSpaceships());
        double distance = calcDistance(entity, closest);
        double angle = calcAngle(entity, closest);

        float distanceFromWallX;
        if (entity.getPosition().x < 500)
            distanceFromWallX = entity.getPosition().x;
        else
            distanceFromWallX = 1000 - entity.getPosition().x;

        float distanceFromWallY;
        if (entity.getPosition().y < 500)
            distanceFromWallY = entity.getPosition().y;
        else
            distanceFromWallY = 1000 - entity.getPosition().y;


        Bullet closestBullet = findClosestBulet(entity, World.getInstance().getBullets());
        double bulletAngle = 0;
        double bulletDistance = 0;
        if (closestBullet != null) {
            bulletAngle = calcAngle(entity, closestBullet);
            bulletDistance = calcDistance(entity, closestBullet);
        }


        Matrix m = nn.calculate(distance, angle, distanceFromWallX, distanceFromWallY, bulletDistance, bulletAngle);


        Vector2 newVelocity = new Vector2((float) (m.getData()[0][0] - 0.5) * PLAYER_SPEED, (float) (m.getData()[1][0] - 0.5) * PLAYER_SPEED);
        Vector2 bulletVelocity = new Vector2((float) (m.getData()[2][0] - 0.5) * Config.BULLET_SPEED, (float) (m.getData()[3][0] - 0.5) * Config.BULLET_SPEED);


        float shootBulet = (float) (m.getData()[4][0]);

        if (shootBulet > 0.5 && (World.getInstance().getIteration() - iterationShoot) > 30) {
            Bullet bullet = new Bullet(entity, bulletVelocity);
            iterationShoot = World.getInstance().getIteration();
            World.getInstance().add(bullet);

        }


        entity.getVelocity().set(newVelocity);
    }


    private Bullet findClosestBulet(SpaceshipEntity me, ArrayList<Bullet> bullets) {
        if (bullets.size() == 0)
            return null;

        Bullet closest = bullets.get(0);
        double closestDistance = Double.MAX_VALUE;


        for (Bullet entity : bullets) {

            double distance = calcDistance(me, entity);

            if (distance < closestDistance) {
                closest = entity;
                closestDistance = distance;
            }

        }

        return closest;
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

    private double calcDistance(SpaceshipEntity e1, Bullet e2) {
        return Math.sqrt(Math.pow(e1.getPosition().x - e2.getPosition().x, 2) + Math.pow(e1.getPosition().y - e2.getPosition().y, 2));
    }

    private double calcAngle(SpaceshipEntity e1, SpaceshipEntity e2) {
        double deltaY = e1.getPosition().y - e2.getPosition().y;
        double deltaX = e1.getPosition().x - e2.getPosition().x;


        return Math.atan2(deltaY, deltaX) * 180 / Math.PI;
    }

    private double calcAngle(SpaceshipEntity e1, Bullet e2) {
        double deltaY = e1.getPosition().y - e2.getPosition().y;
        double deltaX = e1.getPosition().x - e2.getPosition().x;


        return Math.atan2(deltaY, deltaX) * 180 / Math.PI;
    }

}
