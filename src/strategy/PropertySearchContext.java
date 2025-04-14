package src.strategy;

public class PropertySearchContext<T> {
    private PropertySearchStrategy<T> searchStrategy;

    public PropertySearchContext(PropertySearchStrategy<T> strategy) {
        this.searchStrategy = strategy;
    }

    public void setSearchStrategy(PropertySearchStrategy<T> searchStrategy) {
        if (searchStrategy == null) {
            throw new IllegalArgumentException("Search strategy cannot be null");
        }
        this.searchStrategy = searchStrategy;
    }
    public T makeSearch(int[] centerAddress, int radius) {
        if (searchStrategy == null) {
            throw new IllegalStateException("Search strategy not set");
        }
        return searchStrategy.search(centerAddress, radius);
    }
} 