package src.strategy;

/**
 * Context class for the Strategy pattern implementation in property searches.
 * This class provides a unified interface for executing different search strategies
 * and allows for runtime strategy switching.
 * 
 * The context maintains a reference to a strategy object and delegates
 * search operations to the current strategy. This allows clients to:
 * - Set different search strategies at runtime
 * - Execute searches without knowing the specific strategy implementation
 * - Switch between different search algorithms dynamically
 * 
 * @param <T> The type of result returned by the search strategy
 * @see src.strategy.PropertySearchStrategy
 */
public class PropertySearchContext<T> {
    
    /** The current search strategy being used */
    private PropertySearchStrategy<T> searchStrategy;

    /**
     * Constructs a search context with the specified strategy.
     * 
     * @param strategy The initial search strategy to use
     */
    public PropertySearchContext(PropertySearchStrategy<T> strategy) {
        this.searchStrategy = strategy;
    }

    /**
     * Sets a new search strategy for this context.
     * Allows runtime switching between different search algorithms.
     * 
     * @param searchStrategy The new search strategy to use
     * @throws IllegalArgumentException if searchStrategy is null
     */
    public void setSearchStrategy(PropertySearchStrategy<T> searchStrategy) {
        if (searchStrategy == null) {
            throw new IllegalArgumentException("Search strategy cannot be null");
        }
        this.searchStrategy = searchStrategy;
    }
    
    /**
     * Executes a search using the current strategy.
     * 
     * @param centerAddress The center point coordinates for the search
     * @param radius The search radius in Manhattan distance units
     * @return The search result, type depends on the current strategy
     * @throws IllegalStateException if no search strategy is set
     */
    public T makeSearch(int[] centerAddress, int radius) {
        if (searchStrategy == null) {
            throw new IllegalStateException("Search strategy not set");
        }
        return searchStrategy.search(centerAddress, radius);
    }
} 
