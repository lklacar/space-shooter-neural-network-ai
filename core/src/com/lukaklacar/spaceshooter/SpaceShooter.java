package com.lukaklacar.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lukaklacar.spaceshooter.graphics.screens.GameScreen;

public class SpaceShooter extends Game {
    SpriteBatch batch;

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    private InputMultiplexer inputMultiplexer;

    @Override
    public void create() {
        batch = new SpriteBatch();

        setScreen(new GameScreen(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        getScreen().render(Gdx.graphics.getDeltaTime());
        batch.end();
    }
}
