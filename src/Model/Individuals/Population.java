package Model.Individuals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * Class that represents a generic population.
 * @param <T> Individual
 */
public abstract class Population<T extends Individual> extends HashSet<T> implements Comparable<Population<T>> {

    private long id;
    ///Id of the generation (population)
    private T bestIndividual;
    ///Best individual of the population

    /**
     * Constructor of population with id.
     * @param id Id of the population.
     */
    public Population(long id) {
        this.id = id;
        this.bestIndividual = null;
    }
    
    /**
     * Copy constructor.
     * @param p Other population.
     */
    public Population(Population p){
        this.id = p.getId();
        this.bestIndividual = (T)p.getBestIndividual();
    }

    /**
     * Getter of id.
     * @return Id.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter of id.
     * @param id id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter of best individual.
     * @return The best individual (by fitness) of the population.
     */
    public T getBestIndividual() {
        return bestIndividual;
    }

    /**
     * Setter of best individual.
     * @param bestIndividual Best individual.
     */
    public void setBestIndividual(T bestIndividual) {
        this.bestIndividual = bestIndividual;
    }

    /**
     * Getter of average fitness.
     * @return Populations average fitness.
     */
    public double getAverageFitness() {
        return super.stream()
                .mapToDouble(Individual::getFitness)
                .average().orElse(0.0);
    }
    
    /**
     * Get an arrayList of the population.
     * @return Population converted to arrayList.
     */
    public ArrayList<T> getArrayList(){
        return new ArrayList<>(this);
    }

    /**
     * Comparing two populations means comparing their average fitness scores.
     * @param other Population to be compared
     */
    @Override
    public int compareTo(Population other) {
        return Double.compare(getAverageFitness(), other.getAverageFitness());
    }

    /**
     * Clone of population
     * @return A cloned version of population.
     */
    @Override
    public Population<T> clone() {
        return (Population<T>) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Population<?> that = (Population<?>) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
