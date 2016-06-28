package com.lukaklacar.spaceshooter.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.controllers.Controller;

/**
 * Created by Luka on 6/3/2016.
 */

public class SpaceshipEntity {


    private Vector2 position;
    private Vector2 size;
    private Vector2 velocity;

    private Controller controller;

    public SpaceshipEntity(Vector2 position, Vector2 size, Controller controller) {


        this.position = position;
        this.velocity = new Vector2();
        this.size = size;
        this.controller = controller;

    }

    public void update(SpriteBatch batch) {
        position = position.add(velocity);
        controller.update(this);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
