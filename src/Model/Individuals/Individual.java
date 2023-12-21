package Model.Individuals;

/**
 * Class that represents a general individual of a population
 */
public abstract class Individual implements Comparable<Individual>, Cloneable {

    protected double fitness;
    ///Fitness of the individual

    /**
     * Getter of fitness.
     * @return Fitness of individual.
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * Setter of fitness.
     * @param fitness Fitness to apply to individual.
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * Comparing two individuals means comparing their fitness scores.
     * @param other Individual to compare.
     * @return Compare of the two fitness.
     */
    @Override
    public int compareTo(Individual other) {
        return Double.compare(this.fitness, other.fitness);
    }

    /**
     * Clone of individual.
     * @return A clone of the individual.
     * @throws CloneNotSupportedException 
     */
    @Override
    public Individual clone() throws CloneNotSupportedException {
        return (Individual) super.clone();
    }

    /**
     * ToString of individual.
     * @return "Individual{fitness}".
     */
    @Override
    public String toString() {
        return "Individual{" +
                "fitness=" + fitness +
                '}';
    }
}
