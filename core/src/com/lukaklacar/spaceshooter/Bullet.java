package com.lukaklacar.spaceshooter;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;

/**
 * Created by Luka on 6/28/2016.
 */
public class Bullet {

    private SpaceshipEntity shooter;
    private Vector2 position;
    private Vector2 velocity;


    public Bullet(SpaceshipEntity shooter, Vector2 velocity) {
        this.shooter = shooter;
        this.position = new Vector2(shooter.getPosition().x, shooter.getPosition().y);
        this.velocity = velocity;
    }

    public void update() {
        position = position.add(velocity);
    }


    public SpaceshipEntity getShooter() {
        return shooter;
    }

    public void setShooter(SpaceshipEntity shooter) {
        this.shooter = shooter;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }


}
