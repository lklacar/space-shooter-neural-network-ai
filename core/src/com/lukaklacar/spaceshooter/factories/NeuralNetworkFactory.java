package com.lukaklacar.spaceshooter.factories;

import com.lukaklacar.spaceshooter.neuralnetwork.NeuralNetwork;
import com.lukaklacar.spaceshooter.neuralnetwork.SigmoidActivationFunction;

/**
 * Created by Luka on 7/8/2016.
 */
public class NeuralNetworkFactory {

    public static NeuralNetwork createNeuralNetwork() {
        return new NeuralNetwork(4, new int[]{200, 200, 200}, 4, new SigmoidActivationFunction());
    }
}
