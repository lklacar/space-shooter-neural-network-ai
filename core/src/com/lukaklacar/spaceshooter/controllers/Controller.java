package com.lukaklacar.spaceshooter.controllers;

import com.lukaklacar.spaceshooter.entities.PhysicsEntity;

/**
 * Created by Luka on 6/7/2016.
 */
public abstract class Controller<T extends PhysicsEntity> {

    public abstract void update(T entity);

}
