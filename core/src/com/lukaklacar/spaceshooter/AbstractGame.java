package com.lukaklacar.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.genetic.GeneticAlgorithm;
import com.lukaklacar.spaceshooter.listeners.ContactEventArgs;
import com.lukaklacar.spaceshooter.listeners.ContactListener;
import com.lukaklacar.spaceshooter.world.AbstractWorld;
import com.lukaklacar.spaceshooter.world.SpaceShooterWorld;
import com.lukaklacar.spaceshooter.world.renderer.AbstractWorldRenderer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Luka on 6/28/2016.
 */
public abstract class AbstractGame extends Game {

    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private AbstractWorld world;
    private AbstractWorldRenderer worldRenderer;

    @Override
    public void create() {
        init();
        camera = getCamera();
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        world = getWorld();
        worldRenderer = getWorldRenderer();

    }

    public abstract void init();

    public abstract OrthographicCamera getCamera();

    public abstract AbstractWorld getWorld();

    public abstract AbstractWorldRenderer getWorldRenderer();

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        worldRenderer.render(renderer, batch);

        world.update();

        renderer.end();

    }
}

