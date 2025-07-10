package src.strategy;

import java.util.Iterator;
import java.util.List;
import src.util.Property;
import src.util.RadiusCalculator;

/**
 * Strategy for searching properties by their sale status within a specified radius.
 * Part of the Strategy pattern implementation that allows filtering properties
 * based on whether they are sold or available for sale.
 * 
 * This strategy:
 * 1. Gets all properties within the specified radius
 * 2. Filters them based on their sale status
 * 3. Returns the matching properties
 * 
 * The search can be configured to find either:
 * - Properties that are sold (status = true)
 * - Properties that are available for sale (status = false)
 * 
 * @see src.strategy.PropertySearchStrategy
 * @see src.util.Property
 * @see src.util.RadiusCalculator
 */
public class SearchByStatusStrategy implements PropertySearchStrategy<List<Property>> {
    
    /** Flag indicating whether to search for sold properties (true) or available properties (false) */
    private final boolean searchForSold;

    /**
     * Constructs a status search strategy.
     * 
     * @param searchForSold true to search for sold properties, false to search for available properties
     */
    public SearchByStatusStrategy(boolean searchForSold) {
        this.searchForSold = searchForSold;
    }

    /**
     * Searches for properties by status within the specified radius.
     * 
     * @param centerAddress The center point coordinates for the search
     * @param radius The search radius in Manhattan distance units
     * @return A list of properties matching the specified status within the radius
     * @throws IllegalArgumentException if centerAddress is null, has less than 2 coordinates,
     *                                  or if radius is negative
     */
    @Override
    public List<Property> search(int[] centerAddress, int radius) {
        if (centerAddress == null || centerAddress.length < 2) {
            throw new IllegalArgumentException("Center address must contain at least street and avenue coordinates");
        }
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        
        // Get all properties within the radius
        List<Property> propertiesInRadius = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        
        // Filter properties based on status
        Iterator<Property> iterator = propertiesInRadius.iterator();
        while (iterator.hasNext()) {
            Property property = iterator.next();
            if (property.getStatus() != searchForSold) {
                iterator.remove();
            }
        }
        
        return propertiesInRadius;
    }
} 
