package com.lukaklacar.spaceshooter.controllers;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.entities.AISpaceshipEntity;
import com.lukaklacar.spaceshooter.entities.Bullet;
import com.lukaklacar.spaceshooter.entities.PhysicsEntity;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.math.Matrix;
import com.lukaklacar.spaceshooter.neuralnetwork.NeuralNetwork;
import com.lukaklacar.spaceshooter.util.MathUtil;
import com.lukaklacar.spaceshooter.world.SpaceShooterWorld;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Luka on 6/7/2016.
 */
public class AIController extends Controller<SpaceshipEntity> implements Serializable {

    private NeuralNetwork neuralNetwork;
    private int iter = 0;
    private int lastTimeShoot = 0;

    public AIController(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    @Override
    public void update(SpaceshipEntity entity) {
        entity.setThrust(1.0f);
        PhysicsEntity closestEnemy = findClosestEntity(entity);


        double distanceFromEnemy = MathUtil.getDistance(entity.getPosition(), closestEnemy.getPosition());
        double enemyAngle = MathUtil.getAngle(entity.getPosition(), closestEnemy.getPosition());

        PhysicsEntity closestBullet = findClosestBullet(entity);
        double distanceFromBullet = MathUtil.getDistance(entity.getPosition(), closestBullet.getPosition());
        double bulletAngle = MathUtil.getAngle(entity.getPosition(), closestBullet.getPosition());


        Matrix output = neuralNetwork.calculate(distanceFromEnemy, enemyAngle, distanceFromBullet, bulletAngle);

        entity.setThrust((float) output.getData()[0][0]);
        entity.setAngle(entity.getAngle() + ((float) output.getData()[1][0] - 0.5f) * 5);

        iter++;

        if (iter - lastTimeShoot > 100 && output.getData()[3][0] > 0.5f) {
            lastTimeShoot = iter;
            float x = ((float) output.getData()[1][0] - 0.5f) * 5;
            float y = ((float) output.getData()[2][0] - 0.5f) * 5;


            Vector2 bulletVelocity = new Vector2().set(closestEnemy.getPosition());

            bulletVelocity = bulletVelocity.sub(entity.getPosition());

            bulletVelocity.nor();
            bulletVelocity.set(bulletVelocity.x * 10 + x, bulletVelocity.y * 10 + y);


            entity.shootBullet(bulletVelocity);
        }


    }


    private PhysicsEntity findClosestBullet(SpaceshipEntity me) {

        ArrayList<PhysicsEntity> allEntities = SpaceShooterWorld.getInstance().getEntities();
        PhysicsEntity closest = SpaceShooterWorld.getInstance().getEntities().get(0);
        double closestDistance = Double.MAX_VALUE;


        for (PhysicsEntity entity : allEntities) {
            if (entity == me || !(entity instanceof Bullet) || ((Bullet) entity).getShooter() == me)
                continue;

            double distance = MathUtil.getDistance(me.getPosition(), entity.getPosition());

            if (distance < closestDistance) {
                closest = entity;
                closestDistance = distance;
            }

        }

        return closest;

    }

    private PhysicsEntity findClosestEntity(SpaceshipEntity me) {
        ArrayList<PhysicsEntity> allEntities = SpaceShooterWorld.getInstance().getEntities();
        PhysicsEntity closest = SpaceShooterWorld.getInstance().getEntities().get(0);
        double closestDistance = Double.MAX_VALUE;


        for (PhysicsEntity entity : allEntities) {
            if (entity == me || !(entity instanceof AISpaceshipEntity))
                continue;

            double distance = MathUtil.getDistance(me.getPosition(), entity.getPosition());

            if (distance < closestDistance) {
                closest = entity;
                closestDistance = distance;
            }

        }

        return closest;
    }


    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }
}
