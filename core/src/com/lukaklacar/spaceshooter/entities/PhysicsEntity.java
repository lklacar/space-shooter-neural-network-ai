package com.lukaklacar.spaceshooter.entities;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.config.Config;
import com.lukaklacar.spaceshooter.controllers.AIController;
import com.lukaklacar.spaceshooter.controllers.Controller;

import java.io.Serializable;

/**
 * Created by Luka on 7/8/2016.
 */
@SuppressWarnings("ALL")
public abstract class PhysicsEntity implements Serializable {
    protected Vector2 position;
    protected Vector2 size;
    protected Controller controller;
    protected float angle;
    protected float thrust;


    public PhysicsEntity(Vector2 position, Vector2 size) {
        this.position = position;
        this.angle = 0f;

        this.size = size;
    }

    public PhysicsEntity() {
        controller = getController();
    }

    public abstract Controller getController();

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void setController(AIController controller) {
        this.controller = controller;
    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getThrust() {
        return thrust;
    }

    public void setThrust(float thrust) {
        this.thrust = thrust;
    }

    public void update() {
        Vector2 velocity = new Vector2(0, (float) Config.PLAYER_SPEED * thrust);
        velocity = velocity.setAngle(angle);
        position = position.add(velocity);


        if (controller != null)
            controller.update(this);
    }
}
