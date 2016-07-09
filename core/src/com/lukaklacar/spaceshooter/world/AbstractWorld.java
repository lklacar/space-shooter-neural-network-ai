package com.lukaklacar.spaceshooter.world;

import com.badlogic.ashley.core.Entity;
import com.lukaklacar.spaceshooter.entities.PhysicsEntity;
import com.lukaklacar.spaceshooter.listeners.ContactEventArgs;
import com.lukaklacar.spaceshooter.listeners.ContactListener;
import com.lukaklacar.spaceshooter.listeners.Listener;
import com.lukaklacar.spaceshooter.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luka on 6/28/2016.
 */
public abstract class AbstractWorld {

    public ArrayList<PhysicsEntity> getEntities() {
        return entities;
    }

    private ArrayList<PhysicsEntity> entities;
    private ArrayList<Listener> listeners;


    public AbstractWorld() {
        entities = new ArrayList<PhysicsEntity>();
        listeners = new ArrayList<Listener>();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }


    public void update() {
        for (int i = 0; i < entities.size(); i++) {

            entities.get(i).update();

            for (int j = i + 1; j < entities.size(); j++) {

                PhysicsEntity e1 = entities.get(i);
                PhysicsEntity e2 = entities.get(j);

                if (e1 == e2)
                    continue;


                if (MathUtil.getDistance(e1.getPosition(), e2.getPosition()) < e1.getSize().x + e2.getSize().y) {

                    for (Listener l : listeners) {
                        if (l instanceof ContactListener) {
                            ((ContactListener) l).execute(new ContactEventArgs(e1, e2));
                        }

                    }


                }

            }

        }

    }

    public void addEntity(PhysicsEntity entity) {
        entities.add(entity);
    }


}


