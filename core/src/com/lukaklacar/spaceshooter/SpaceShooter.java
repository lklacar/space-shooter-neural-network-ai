package com.lukaklacar.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.genetic.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Luka on 6/28/2016.
 */
public class SpaceShooter extends Game {

    private ShapeRenderer renderer;
    private OrthographicCamera camera;

    @Override
    public void create() {
        renderer = new ShapeRenderer();

        camera = new OrthographicCamera(3000 * (16.0f / 9.0f), 3000);
        camera.translate(500, 500);

        World.getInstance().populate(20);

        int iter = 0;
        for (int i = 0; i < 500 * 1000; i++) {
            World.getInstance().simulateOneIteration();
            if (World.getInstance().getIteration() % 1000 == 0) {
                iter++;
                System.out.println(iter);

                int num = 0;
                float sum = 0;
                for (SpaceshipEntity e : World.getInstance().getSpaceships()) {
                    if (e.getFitness() > 0) {
                        sum += e.getFitness();
                        num++;
                    }
                }
                Collections.sort(World.getInstance().getSpaceships());
                System.out.println(sum / num);
                System.out.println(World.getInstance().getSpaceships().get(World.getInstance().getSpaceships().size() - 1).getFitness());
                System.out.println();


                World.getInstance().setSpaceships(new GeneticAlgorithm().generateNewPopulation(World.getInstance().getSpaceships()));
                World.getInstance().getBullets().clear();


            }

        }


    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float sumX = 0, sumY = 0;
        for (SpaceshipEntity s : World.getInstance().getSpaceships()) {
            sumX += s.getPosition().x;
            sumY += s.getPosition().y;
        }

        camera.position.set(sumX / World.getInstance().getSpaceships().size(), sumY / World.getInstance().getSpaceships().size(), 0);

        camera.update();


        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        World.getInstance().simulateOneIteration();

        renderer.setColor(Color.WHITE);

        Collections.sort(World.getInstance().getSpaceships());

        for (SpaceshipEntity entity : World.getInstance().getSpaceships())
            renderer.rect(entity.getPosition().x, entity.getPosition().y, entity.getSize().x, entity.getSize().y);

        renderer.setColor(Color.BLUE);
        SpaceshipEntity entity2 = World.getInstance().getSpaceships().get(World.getInstance().getSpaceships().size() - 1);
        renderer.rect(entity2.getPosition().x, entity2.getPosition().y, entity2.getSize().x, entity2.getSize().y);

        renderer.setColor(Color.RED);
        for (Bullet entity : World.getInstance().getBullets())
            renderer.circle(entity.getPosition().x, entity.getPosition().y, 5, 5);


        if (World.getInstance().getIteration() % 1000 == 0) {


            int num = 0;
            float sum = 0;
            for (SpaceshipEntity e : World.getInstance().getSpaceships()) {
                if (e.getFitness() > 0) {
                    sum += e.getFitness();
                    num++;
                }
            }
            Collections.sort(World.getInstance().getSpaceships());
            System.out.println(sum / num);
            System.out.println(World.getInstance().getSpaceships().get(World.getInstance().getSpaceships().size() - 1).getFitness());
            System.out.println();


            World.getInstance().setSpaceships(new GeneticAlgorithm().generateNewPopulation(World.getInstance().getSpaceships()));
            World.getInstance().getBullets().clear();


        }


        renderer.end();

    }
}

