package src.strategy;

/**
 * Generic interface for property search strategies in the real estate system.
 * Part of the Strategy pattern implementation that allows different search algorithms
 * to be used interchangeably.
 * 
 * This interface uses generics to support different return types:
 * - List&lt;Property&gt; for searches that return property collections
 * - Double for searches that return calculated values (like average price)
 * 
 * The Strategy pattern allows the system to support various search criteria:
 * - Search by property status (sold/available)
 * - Search by price range
 * - Search by average price calculation
 * 
 * All searches are performed within a specified radius from a center address
 * using Manhattan distance calculations.
 * 
 * @param <T> The type of result returned by the search strategy
 * @see src.strategy.SearchByStatusStrategy
 * @see src.strategy.SearchByPriceStrategy
 * @see src.strategy.SearchByAveragePriceStrategy
 * @see src.strategy.PropertySearchContext
 */
public interface PropertySearchStrategy<T> {
    
    /**
     * Performs a search for properties within a specified radius from a center address.
     * 
     * @param centerAddress The center point coordinates for the search (must have at least 2 elements)
     * @param radius The search radius in Manhattan distance units (must be non-negative)
     * @return The search result, type depends on the concrete strategy implementation
     * @throws IllegalArgumentException if centerAddress is null, has less than 2 coordinates,
     *                                  or if radius is negative
     */
    T search(int[] centerAddress, int radius);
} 
