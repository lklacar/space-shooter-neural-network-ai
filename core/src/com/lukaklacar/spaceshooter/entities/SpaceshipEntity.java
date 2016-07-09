package com.lukaklacar.spaceshooter.entities;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.config.Config;
import com.lukaklacar.spaceshooter.controllers.AIController;
import com.lukaklacar.spaceshooter.controllers.Controller;
import com.lukaklacar.spaceshooter.world.AbstractWorld;
import com.lukaklacar.spaceshooter.world.SpaceShooterWorld;

/**
 * Created by Luka on 6/3/2016.
 */

public abstract class SpaceshipEntity extends PhysicsEntity implements Comparable<SpaceshipEntity> {

    private int fitness;

    public SpaceshipEntity(Vector2 position, Vector2 size, Vector2 velocity) {
        super(position, size);
        fitness = 0;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void shootBullet(Vector2 velocity) {
        Bullet bullet = new Bullet(this);
        bullet.setPosition(new Vector2(position.x, position.y));
        bullet.setAngle(angle);
        bullet.setSize(new Vector2(5, 5));
        bullet.setVelocity(velocity);

        SpaceShooterWorld.getInstance().getEntities().add(bullet);
    }

    public void addFitness(){
        fitness++;
    }

    public void removeFitness(){
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

    @Override
    public abstract Controller getController();
}
