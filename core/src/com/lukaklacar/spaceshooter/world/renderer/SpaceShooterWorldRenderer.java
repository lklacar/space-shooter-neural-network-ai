package com.lukaklacar.spaceshooter.world.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lukaklacar.spaceshooter.entities.PhysicsEntity;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.world.AbstractWorld;

/**
 * Created by Luka on 7/8/2016.
 */
public class SpaceShooterWorldRenderer extends AbstractWorldRenderer {

    private BitmapFont font;

    public SpaceShooterWorldRenderer(AbstractWorld world) {
        super(world);

        font = new BitmapFont();
    }


    @Override
    public void render(ShapeRenderer renderer, SpriteBatch batch) {

        for (int i = -4000; i < 4000; i += 50) {
            for (int j = -4000; j < 4000; j += 50) {
                renderer.setColor(Color.DARK_GRAY);
                renderer.setAutoShapeType(true);
                renderer.set(ShapeRenderer.ShapeType.Line);
                renderer.line(i, j, i + 4000, j);
                renderer.line(i, j, i, j + 4000);

            }

        }


        renderer.setColor(Color.WHITE);
        for (PhysicsEntity entity : getWorld().getEntities()) {
            renderer.ellipse(entity.getPosition().x, entity.getPosition().y, entity.getSize().x, entity.getSize().y);
            if (entity instanceof SpaceshipEntity) {
                batch.begin();
                font.draw(batch, Integer.toString(((SpaceshipEntity) entity).getFitness()), entity.getPosition().x, entity.getPosition().y);
                batch.end();
            }

        }


    }
}