package com.lukaklacar.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.entities.AISpaceshipEntity;
import com.lukaklacar.spaceshooter.entities.PhysicsEntity;
import com.lukaklacar.spaceshooter.genetic.GeneticAlgorithm;
import com.lukaklacar.spaceshooter.util.MathUtil;
import com.lukaklacar.spaceshooter.world.AbstractWorld;
import com.lukaklacar.spaceshooter.world.SpaceShooterWorld;
import com.lukaklacar.spaceshooter.world.renderer.AbstractWorldRenderer;
import com.lukaklacar.spaceshooter.world.renderer.SpaceShooterWorldRenderer;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Luka on 7/8/2016.
 */
public class SpaceShooterGame extends AbstractGame {
    private AbstractWorld world;
    private OrthographicCamera camera;
    private AbstractWorldRenderer renderer;
    private GeneticAlgorithm geneticAlgorithm;

    @Override
    public void init() {
        world = SpaceShooterWorld.getInstance();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        camera.update();
        renderer = new SpaceShooterWorldRenderer(world);

        geneticAlgorithm = new GeneticAlgorithm();


/*
        ObjectInputStream oos = null;
        try {
            oos = new ObjectInputStream(new FileInputStream("population_1.dat"));

            setPopulation((ArrayList<AISpaceshipEntity>) oos.readObject());
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


        populate(20);

        train(1000, 500);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("population.dat"));
            oos.writeObject(getPopulation());
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void centerCameraToBest() {
        AISpaceshipEntity best = getPopulation().get(0);

        for (AISpaceshipEntity entity : getPopulation()) {
            if (entity.getFitness() > best.getFitness()) {
                best = entity;
            }
        }

        camera.position.set(best.getPosition().x, best.getPosition().y, 0);
        camera.update();

    }


    @Override
    public void render() {
        super.render();

        centerCameraToBest();


    }

    private void train(int generationLength, int numberOfGenerations) {
        for (int j = 0; j < numberOfGenerations; j++) {
            System.out.println("Generation: " + j);
            for (int i = 0; i < generationLength; i++) {
                world.update();
            }

            ArrayList<AISpaceshipEntity> newPopulation = geneticAlgorithm.generateNewPopulation(getPopulation());
            setPopulation(newPopulation);
        }


    }

    private void setPopulation(ArrayList<AISpaceshipEntity> population) {
        world.getEntities().clear();

        for (AISpaceshipEntity entity : population) {
            world.getEntities().add(entity);
        }
    }

    private ArrayList<AISpaceshipEntity> getPopulation() {
        ArrayList<AISpaceshipEntity> population = new ArrayList<AISpaceshipEntity>();

        for (PhysicsEntity entity : world.getEntities()) {
            if (entity instanceof AISpaceshipEntity)
                population.add((AISpaceshipEntity) entity);
        }

        return population;


    }

    private void populate(int number) {
        for (int i = 0; i < number; i++) {
            world.addEntity(new AISpaceshipEntity(new Vector2(MathUtil.randInt(0, Gdx.graphics.getWidth()), MathUtil.randInt(0, Gdx.graphics.getHeight())), new Vector2(10, 10), new Vector2(0, 0)));
        }

    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public AbstractWorld getWorld() {
        return world;
    }

    @Override
    public AbstractWorldRenderer getWorldRenderer() {
        return renderer;
    }
}
