package com.lukaklacar.spaceshooter.entities;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.Config;
import com.lukaklacar.spaceshooter.controllers.AIController;
import com.lukaklacar.spaceshooter.controllers.Controller;

/**
 * Created by Luka on 6/3/2016.
 */

public class SpaceshipEntity implements Comparable<SpaceshipEntity> {


    private Vector2 position;
    private Vector2 size;
    private Vector2 velocity;
    private AIController controller;
    private int fitness;

    public AIController getController() {
        return controller;
    }

    public void setController(AIController controller) {
        this.controller = controller;
    }

    public SpaceshipEntity(Vector2 position, AIController controller) {

        this.position = position;
        this.velocity = new Vector2();
        this.size = new Vector2(Config.PLAYER_SIZE, Config.PLAYER_SIZE);
        this.controller = controller;
        fitness = 0;

    }

    public void update() {
        /*
        Vector2 tempPosition = new Vector2(position.x, position.y);
        tempPosition = tempPosition.add(velocity);
        if (tempPosition.x < 1000 - Config.PLAYER_SIZE && tempPosition.y < 1000 - Config.PLAYER_SIZE && tempPosition.x > 0 && tempPosition.y > 0)*/
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

    public Vector2 getSize() {
        return size;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void addFittenss() {
        fitness++;
    }

    public void remoteFitness() {
        fitness--;
    }

    @Override
    public int compareTo(SpaceshipEntity o) {
        if (getFitness() < o.getFitness())
            return -1;
        else if (getFitness() > o.getFitness())
            return 1;

        return 1;

    }
}
