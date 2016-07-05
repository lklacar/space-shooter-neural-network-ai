package com.lukaklacar.spaceshooter.genetic;

import com.lukaklacar.spaceshooter.controllers.AIController;
import com.lukaklacar.spaceshooter.entities.SpaceshipEntity;
import com.lukaklacar.spaceshooter.math.Matrix;
import com.lukaklacar.spaceshooter.neuralnetwork.NeuralNetwork;
import com.lukaklacar.spaceshooter.neuralnetwork.SigmoidActivationFunction;
import com.lukaklacar.spaceshooter.util.MathUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class GeneticAlgorithm implements Serializable {

    private static final long serialVersionUID = 1343352967654384459L;

    public ArrayList<SpaceshipEntity> generateNewPopulation(ArrayList<SpaceshipEntity> population) {

        ArrayList<SpaceshipEntity> newPopulation = new ArrayList<SpaceshipEntity>();
        Collections.sort(population);


        for (int i = 0; i < population.size() / 2; i++) {
            int first = choose(population);
            int second = choose(population);
            while (second == first)
                second = choose(population);

            newPopulation.addAll(generateNewPair(population.get(first),
                    population.get(second)));

        }

        return newPopulation;

    }

    private ArrayList<SpaceshipEntity> generateNewPair(SpaceshipEntity a,
                                                       SpaceshipEntity b) {
        Random random = new Random();
        NeuralNetwork first = new NeuralNetwork(6, new int[]{200, 200, 200}, 5, new SigmoidActivationFunction());
        NeuralNetwork second = new NeuralNetwork(6, new int[]{200, 200, 200}, 5, new SigmoidActivationFunction());


        double mutateTolerance = 0.02;
        for (int k = 0; k < a.getController().getNn().getMatrices().size(); ++k) {
            Matrix M = a.getController().getNn().getMatrices().get(k);
            Matrix N = b.getController().getNn().getMatrices().get(k);
            int n = random.nextInt(M.getN());
            for (int i = 0; i < M.getM(); ++i) {
                for (int j = 0; j < M.getN(); ++j) {

                    if (j < n) {
                        if (Math.random() < mutateTolerance) {
                            first.getMatrices().get(k).getData()[i][j] = mutate();
                        } else {
                            first.getMatrices().get(k).getData()[i][j] = M
                                    .getData()[i][j];
                        }
                        if (Math.random() < mutateTolerance) {
                            second.getMatrices().get(k).getData()[i][j] = mutate();
                        } else {
                            second.getMatrices().get(k).getData()[i][j] = N
                                    .getData()[i][j];
                        }
                    } else {
                        if (Math.random() < mutateTolerance) {
                            first.getMatrices().get(k).getData()[i][j] = mutate();
                        } else {
                            first.getMatrices().get(k).getData()[i][j] = N
                                    .getData()[i][j];
                        }
                        if (Math.random() < mutateTolerance) {
                            second.getMatrices().get(k).getData()[i][j] = mutate();
                        } else {
                            second.getMatrices().get(k).getData()[i][j] = M
                                    .getData()[i][j];
                        }
                    }

                }
            }
        }

        AIController controller1 = new AIController(first);
        AIController controller12 = new AIController(second);


        ArrayList<SpaceshipEntity> pair = new ArrayList<SpaceshipEntity>();

        pair.add(new SpaceshipEntity(MathUtil.randVector(0, 1000), controller1));
        pair.add(new SpaceshipEntity(MathUtil.randVector(0, 1000), controller12));


        return pair;
    }

    private double mutate() {
        return MathUtil.randRange(-4, 4);
    }

    private int choose(ArrayList<SpaceshipEntity> population) {


        Collections.sort(population);
        int lowest = population.get(0).getFitness();
        int highest = population.get(population.size() - 1).getFitness();

        for (SpaceshipEntity entity : population) {
            entity.setFitness(entity.getFitness() + Math.abs(lowest));
        }


        double sum = 0;
        for (SpaceshipEntity nn : population)
            sum += nn.getFitness();

        double random = MathUtil.randRange(0, sum);

        sum = 0;
        for (int i = 0; i < population.size(); i++) {
            sum += population.get(i).getFitness();
            if (sum > random)
                return i;
        }

        return 0;


    }


}
