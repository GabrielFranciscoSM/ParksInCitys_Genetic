package Model.operators.crossover;

import Model.Individuals.Individual;
import Model.Individuals.Population;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CrossoverOperator<T extends Individual> {

    class Pairing {
        protected final T firstParent;
        protected final T secondParent;

        protected Pairing(T firstParent, T secondParent) {
            this.firstParent = firstParent;
            this.secondParent = secondParent;
        }
    }

    protected ArrayList<Pairing> makeRandomPairings(Population<T> population) {
        ArrayList<Pairing> pairings = new ArrayList<>();
        ArrayList<T> populationList = new ArrayList<>(population);
        // If there is only one individual, pair with itself!
        if (population.size() < 2) {
            T loneIndividual = populationList.get(0);
            Pairing pairing = new Pairing(loneIndividual, loneIndividual);
            pairings.add(pairing);
        } else {
            // Convert to list and randomly permute it
            Collections.shuffle(populationList);
            // If odd, the last element is not considered
            if (populationList.size() % 2 != 0) {
                populationList.remove(populationList.size() - 1);
            }
            for (int i = 0; i < populationList.size(); i = i + 2) {
                T firstParent = populationList.get(i);
                T secondParent = populationList.get(i + 1);
                Pairing pairing = new Pairing(firstParent, secondParent);
                pairings.add(pairing);
            }
        }
        return pairings;
    }

}
