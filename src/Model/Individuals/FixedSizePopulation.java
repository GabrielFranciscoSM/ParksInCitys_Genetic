package Model.Individuals;


public class FixedSizePopulation<T extends Individual> extends Population<T> {

    final static public int MAXSIZE = 100;
    final static public int MINSIZE = 5;
    
    private final int maxSize;

    public FixedSizePopulation(long id, int maxSize) {
        super(id);
        int msize = Math.max(maxSize, MINSIZE);
        this.maxSize = Math.min(msize, MAXSIZE);
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public boolean add(T individual) {
        if (0 < maxSize && maxSize <= size()) {
            return false;
        }
        super.add(individual);
        return true;
    }

}
