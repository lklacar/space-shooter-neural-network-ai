package com.lukaklacar.spaceshooter;

import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;

import java.util.ArrayList;

/**
 * Created by Luka on 6/28/2016.
 */
public class World {
    private static World ourInstance = new World();

    public static World getInstance() {
        return ourInstance;
    }

    private ArrayList<SpaceshipEntity> spaceships;
    private ArrayList<Bullet> bullets;


    private World() {
        spaceships = new ArrayList<SpaceshipEntity>();
        bullets = new ArrayList<Bullet>();
    }


    public void add(Bullet entity) {
        bullets.add(entity);
    }

    public void add(SpaceshipEntity entity) {
        spaceships.add(entity);
    }

    public static World getOurInstance() {
        return ourInstance;
    }


    public ArrayList<SpaceshipEntity> getSpaceships() {
        return spaceships;
    }


    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }


}
