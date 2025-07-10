package src.util;

import src.main.SystemManager;

import java.util.Iterator;
import java.util.List;

/**
 * Utility class for calculating distances and filtering properties within a radius
 * in Manhattan's grid system.
 * 
 * This class provides functionality to:
 * - Calculate Manhattan distance between two addresses
 * - Filter properties within a specified radius from a center point
 * 
 * Manhattan distance is calculated as the sum of absolute differences of coordinates,
 * which represents the actual walking distance in a grid-based city layout.
 * 
 * @see src.util.Property
 * @see src.main.SystemManager
 */
public class RadiusCalculator {
    /**
     * Calculates the Manhattan distance between two addresses.
     * Uses only the first two coordinates (street and avenue) for distance calculation.
     * 
     * Manhattan distance = |x1 - x2| + |y1 - y2|
     * 
     * @param address1 The first address coordinates
     * @param address2 The second address coordinates
     * @return The Manhattan distance between the two addresses
     */
    private static int calculateManhattanDistance(int[] address1, int[] address2) {
        return (Math.abs(address1[0] - address2[0]) + Math.abs(address1[1] - address2[1]));
    }
    
    /**
     * Filters and returns properties within a specified radius from a center address.
     * 
     * This method:
     * 1. Gets all properties from the SystemManager
     * 2. Calculates Manhattan distance for each property from the center
     * 3. Removes properties that are outside the specified radius
     * 4. Returns the filtered list
     * 
     * @param centerAddress The center point coordinates (must have at least 2 elements)
     * @param radius The search radius in Manhattan distance units (must be non-negative)
     * @return A list of properties within the specified radius
     * @throws IllegalArgumentException if centerAddress is null, has less than 2 coordinates,
     *                                  or if radius is negative
     */
    public static List<Property> getPropertiesInRadius(int[] centerAddress, int radius) {
        if (centerAddress == null || centerAddress.length < 2) {
            throw new IllegalArgumentException("Center address must contain at least street and avenue coordinates");
        }
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }

        // Get copy of all properties
        List<Property> propertiesInRadius = SystemManager.getInstance().getAllProperties();
        
        // Use Iterator to safely remove elements while iterating
        Iterator<Property> iterator = propertiesInRadius.iterator();
        while (iterator.hasNext()) {
            Property property = iterator.next();
            int distance = calculateManhattanDistance(centerAddress, property.getAddress());
            if (distance > radius) {
                iterator.remove();
            }
        }

        return propertiesInRadius;
    }
} 
