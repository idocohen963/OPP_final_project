package src.strategy;

import java.util.List;
import src.util.Property;
import src.util.RadiusCalculator;

/**
 * Strategy for calculating the average price of properties within a specified radius.
 * Part of the Strategy pattern implementation that provides market analysis
 * functionality by computing average property prices in an area.
 * 
 * This strategy:
 * 1. Gets all properties within the specified radius
 * 2. Calculates the sum of all property prices
 * 3. Returns the average price per property
 * 
 * The average price calculation helps with:
 * - Market analysis and pricing trends
 * - Property valuation comparisons
 * - Investment decision making
 * - Market research and reporting
 * 
 * @see src.strategy.PropertySearchStrategy
 * @see src.util.Property
 * @see src.util.RadiusCalculator
 */
public class SearchByAveragePriceStrategy implements PropertySearchStrategy<Double> {
    
    /**
     * Calculates the average price of properties within the specified radius.
     * 
     * @param centerAddress The center point coordinates for the search
     * @param radius The search radius in Manhattan distance units
     * @return The average price of properties in the area, or 0.0 if no properties found
     * @throws IllegalArgumentException if centerAddress is null, has less than 2 coordinates,
     *                                  or if radius is negative
     */
    @Override
    public Double search(int[] centerAddress, int radius) {
        if (centerAddress == null || centerAddress.length < 2) {
            throw new IllegalArgumentException("Center address must contain at least street and avenue coordinates");
        }
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }

        // Get all properties within the radius
        List<Property> propertiesInRadius = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        
        // Return 0.0 if no properties found
        if (propertiesInRadius.isEmpty()) {
            return 0.0;
        }

        // Calculate sum of all property prices
        double sum = 0.0;
        for (Property property : propertiesInRadius) {
            sum += property.getTotalPrice();
        }
        
        // Return average price
        return sum / propertiesInRadius.size();
    }
} 
