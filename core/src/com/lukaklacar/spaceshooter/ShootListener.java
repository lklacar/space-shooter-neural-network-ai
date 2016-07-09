package com.lukaklacar.spaceshooter;

import com.lukaklacar.spaceshooter.entities.AISpaceshipEntity;
import com.lukaklacar.spaceshooter.entities.Bullet;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.listeners.ContactEventArgs;
import com.lukaklacar.spaceshooter.listeners.ContactListener;
import com.lukaklacar.spaceshooter.world.SpaceShooterWorld;

/**
 * Created by Luka on 7/9/2016.
 */
public class ShootListener extends ContactListener {
    @Override
    public void execute(ContactEventArgs args) {
        if (args.getFirst() instanceof Bullet && args.getSecond() instanceof AISpaceshipEntity) {
            if (((Bullet) args.getFirst()).getShooter() != args.getSecond()) {
                ((SpaceshipEntity) args.getSecond()).removeFitness();
                ((Bullet) args.getFirst()).getShooter().addFitness();
                SpaceShooterWorld.getInstance().getEntities().remove(args.getFirst());
            }

        }

        if (args.getFirst() instanceof AISpaceshipEntity && args.getSecond() instanceof Bullet) {
            if (args.getFirst() != ((Bullet) args.getSecond()).getShooter()) {
                ((SpaceshipEntity) args.getFirst()).removeFitness();
                ((Bullet) args.getSecond()).getShooter().addFitness();
                SpaceShooterWorld.getInstance().getEntities().remove(args.getSecond());
            }

        }
    }
}
