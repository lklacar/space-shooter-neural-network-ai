package com.lukaklacar.spaceshooter.graphics.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by l.klacar on 3.6.2016..
 */
public abstract class AbstractBox2dScreen extends ScreenAdapter {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    private float widthInMeters, heightInMeters;

    public AbstractBox2dScreen(float widthInMeters, float heightInMeters) {
        this.widthInMeters = widthInMeters;
        this.heightInMeters = heightInMeters * ((float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());
    }


    @Override
    public void show() {
        super.show();
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(widthInMeters, heightInMeters);
        camera.translate(widthInMeters / 2, heightInMeters / 2);
        camera.update();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        world.step(1 / 60f, 6, 2);
        camera.update();

        debugRenderer.render(world, camera.combined);

    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setDebugRenderer(Box2DDebugRenderer debugRenderer) {
        this.debugRenderer = debugRenderer;
    }
}


