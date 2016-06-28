package com.lukaklacar.spaceshooter.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.lukaklacar.spaceshooter.Config;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;

/**
 * Created by Luka on 6/7/2016.
 */
public class HumanController extends Controller {


    @Override
    public void update(SpaceshipEntity entity) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            entity.getPosition().set(entity.getPosition().x + Config.PLAYER_SPEED, entity.getPosition().y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            entity.getPosition().set(entity.getPosition().x, entity.getPosition().y + Config.PLAYER_SPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            entity.getPosition().set(entity.getPosition().x - Config.PLAYER_SPEED, entity.getPosition().y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            entity.getPosition().set(entity.getPosition().x, entity.getPosition().y - Config.PLAYER_SPEED);
        }
    }
}
