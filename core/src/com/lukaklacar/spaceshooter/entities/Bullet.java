package com.lukaklacar.spaceshooter.entities;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.config.Config;
import com.lukaklacar.spaceshooter.controllers.Controller;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;

/**
 * Created by Luka on 6/28/2016.
 */
public class Bullet extends PhysicsEntity {

    private SpaceshipEntity shooter;
    private Vector2 velocity;

    public Bullet(SpaceshipEntity shooter) {
        this.shooter = shooter;
        velocity = new Vector2();
    }

    @Override
    public void update() {

        position = position.add(velocity);


        if (controller != null)
            controller.update(this);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public SpaceshipEntity getShooter() {
        return shooter;
    }

    @Override
    public Controller getController() {
        return null;
    }
}
