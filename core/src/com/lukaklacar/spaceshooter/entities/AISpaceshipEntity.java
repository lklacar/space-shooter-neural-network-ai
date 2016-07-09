package com.lukaklacar.spaceshooter.entities;

import com.badlogic.gdx.math.Vector2;
import com.lukaklacar.spaceshooter.controllers.AIController;
import com.lukaklacar.spaceshooter.controllers.Controller;
import com.lukaklacar.spaceshooter.factories.NeuralNetworkFactory;
import com.lukaklacar.spaceshooter.neuralnetwork.NeuralNetwork;

/**
 * Created by Luka on 7/8/2016.
 */
public class AISpaceshipEntity extends SpaceshipEntity {

    public AISpaceshipEntity(Vector2 position, Vector2 size, Vector2 velocity, AIController controller) {
        super(position, size, velocity);
        this.controller = controller;
    }

    public AISpaceshipEntity(Vector2 position, Vector2 size, Vector2 velocity) {
        super(position, size, velocity);
        controller = getController();

    }

    @Override
    public void update() {
        super.update();
        controller.update(this);
    }

    @Override
    public Controller getController() {
        return new AIController(NeuralNetworkFactory.createNeuralNetwork());
    }

    public NeuralNetwork getNeuralNetwork() {
        return ((AIController) controller).getNeuralNetwork();
    }
}

