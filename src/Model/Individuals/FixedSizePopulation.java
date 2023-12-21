package Model.Individuals;

/**
 * Fixed size population of individuals
 * @param <T> Individual
 */
public class FixedSizePopulation<T extends Individual> extends Population<T> {

    final static public int MAXSIZE = 500;
    ///Max posible size of population
    final static public int DEFSIZE = 20;
    ///Default max size of population
    final static public int MINSIZE = 2;
    ///Minimal size of population
    
    private final int maxSize;
    ///Actual max size of population

    /**
     * Constructor
     * @param id        Id of the generation (population)
     * @param maxSize   Max size of population
     */
    public FixedSizePopulation(long id, int maxSize) {
        super(id);
        int msize = Math.max(maxSize, MINSIZE);
        this.maxSize = Math.min(msize, MAXSIZE);
    }
    
    /**
     * Getter for max size.
     * @return Max size of the population.
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Override add to ensure the population doesn't grow over the max size.
     * @param individual Individual to add
     * @return           True if can add, false if not
     */
    @Override
    public boolean add(T individual) {
        if (0 < maxSize && maxSize <= size()) {
            return false;
        }
        super.add(individual);
        return true;
    }

}
