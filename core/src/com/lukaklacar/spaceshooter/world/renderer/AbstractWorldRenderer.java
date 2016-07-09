package com.lukaklacar.spaceshooter.world.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.lukaklacar.spaceshooter.world.AbstractWorld;

/**
 * Created by Luka on 7/8/2016.
 */
public abstract class AbstractWorldRenderer {

    private AbstractWorld world;

    public AbstractWorldRenderer(AbstractWorld world) {
        this.world = world;
    }

    public AbstractWorld getWorld() {
        return world;
    }

    public abstract void render(ShapeRenderer renderer, SpriteBatch batch);

}
