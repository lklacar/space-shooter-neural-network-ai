package com.lukaklacar.spaceshooter;

import com.lukaklacar.spaceshooter.controllers.AIController;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.util.MathUtil;

import java.util.ArrayList;

/**
 * Created by Luka on 6/28/2016.
 */
public class World {
    private static World ourInstance = new World();

    private int iteration;

    public static World getInstance() {
        return ourInstance;
    }

    private ArrayList<SpaceshipEntity> spaceships;
    private ArrayList<Bullet> bullets;

    public static void setOurInstance(World ourInstance) {
        World.ourInstance = ourInstance;
    }

    public void setSpaceships(ArrayList<SpaceshipEntity> spaceships) {
        this.spaceships = spaceships;
    }

    private World() {
        spaceships = new ArrayList<SpaceshipEntity>();
        bullets = new ArrayList<Bullet>();
        iteration = 0;
    }


    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
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


    public void simulateOneIteration() {
        for (SpaceshipEntity spaceship : spaceships)
            spaceship.update();

        for (Bullet b : bullets)
            b.update();


        checkHit();


        iteration++;
    }


    private void checkHit() {
        ArrayList<Bullet> toRemove = new ArrayList<Bullet>();
        for (Bullet b : bullets) {
            for (SpaceshipEntity s : spaceships) {
                if (b.getShooter() == s) // Can't hit myself
                    continue;

                if (calcDistance(b, s) < Config.PLAYER_SIZE) {

                    b.getShooter().addFittenss();

                    s.remoteFitness();
                    toRemove.add(b);
                }


            }
        }
        for (Bullet b : toRemove) {

            bullets.remove(b);
        }


    }

    private double calcDistance(Bullet e1, SpaceshipEntity e2) {
        return Math.sqrt(Math.pow(e1.getPosition().x - e2.getPosition().x, 2) + Math.pow(e1.getPosition().y - e2.getPosition().y, 2));
    }

    public void populate(int population) {
        for (int i = 0; i < population; i++) {
            SpaceshipEntity temp = new SpaceshipEntity(MathUtil.randVector(0, 1000), new AIController());
            add(temp);
        }
    }
}
