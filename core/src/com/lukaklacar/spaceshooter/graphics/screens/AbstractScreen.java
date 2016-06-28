package com.lukaklacar.spaceshooter.graphics.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;

import java.util.ArrayList;

/**
 * Created by l.klacar on 3.6.2016..
 */
public abstract class AbstractScreen extends ScreenAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public ArrayList<SpaceshipEntity> getEntities() {
        return entities;
    }

    private ArrayList<SpaceshipEntity> entities;


    private float width, height;

    AbstractScreen(float width, float height) {
        this.width = width;

        // Dynamically calculate height for different aspect ratios
        this.height = height;
        this.batch = new SpriteBatch();
        entities = new ArrayList<SpaceshipEntity>();
    }


    @Override
    public final void show() {
        super.show();

        camera = new OrthographicCamera(width, height);
        camera.translate(width / 2, height / 2);
        camera.update();
    }

    @Override
    public final void render(float delta) {
        super.render(delta);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        renderEntities(batch);

        batch.end();

    }

    private void renderEntities(SpriteBatch batch) {
        for (SpaceshipEntity e : entities)
            e.update(batch);


    }

    public final void addEntity(SpaceshipEntity entity) {
        entities.add(entity);
    }

}


