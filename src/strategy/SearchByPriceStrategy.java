package src.strategy;

import java.util.Iterator;
import java.util.List;
import src.util.Property;
import src.util.RadiusCalculator;

/**
 * Strategy for searching properties by price comparison within a specified radius.
 * Part of the Strategy pattern implementation that allows filtering properties
 * based on their total price relative to a target price.
 * 
 * This strategy:
 * 1. Gets all properties within the specified radius
 * 2. Compares each property's total price to the target price
 * 3. Filters properties based on the specified comparison type
 * 4. Returns the matching properties
 * 
 * Supported comparison types:
 * - HIGHER: Properties with price higher than target
 * - LOWER: Properties with price lower than target  
 * - EQUAL: Properties with price equal to target
 * 
 * @see src.strategy.PropertySearchStrategy
 * @see src.util.Property
 * @see src.util.RadiusCalculator
 */
public class SearchByPriceStrategy implements PropertySearchStrategy<List<Property>> {
    
    /**
     * Enumeration defining the types of price comparisons available.
     */
    public enum PriceComparison {
        /** Find properties with prices higher than the target */
        HIGHER,
        /** Find properties with prices lower than the target */
        LOWER,
        /** Find properties with prices equal to the target */
        EQUAL
    }

    /** The target price for comparison */
    private final double targetPrice;
    
    /** The type of price comparison to perform */
    private final PriceComparison comparison;

    /**
     * Constructs a price search strategy.
     * 
     * @param targetPrice The target price for comparison (must be non-negative)
     * @param comparison The type of price comparison to perform
     * @throws IllegalArgumentException if targetPrice is negative or comparison is null
     */
    public SearchByPriceStrategy(double targetPrice, PriceComparison comparison) {
        if (targetPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (comparison == null) {
            throw new IllegalArgumentException("Price comparison type cannot be null");
        }
        this.targetPrice = targetPrice;
        this.comparison = comparison;
    }

    /**
     * Searches for properties by price comparison within the specified radius.
     * 
     * @param centerAddress The center point coordinates for the search
     * @param radius The search radius in Manhattan distance units
     * @return A list of properties matching the price criteria within the radius
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
        
        // Filter properties based on price comparison
        Iterator<Property> iterator = propertiesInRadius.iterator();
        while (iterator.hasNext()) {
            Property property = iterator.next();
            double propertyPrice = property.getTotalPrice();
            
            // Determine if property should be removed based on comparison type
            boolean shouldRemove = switch (comparison) {
                case HIGHER -> propertyPrice <= targetPrice;
                case LOWER -> propertyPrice >= targetPrice;
                case EQUAL -> propertyPrice != targetPrice;
            };

            if (shouldRemove) {
                iterator.remove();
            }
        }
        
        return propertiesInRadius;
    }
} 
