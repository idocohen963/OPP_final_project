package src.util;

import src.main.SystemManager;

import java.util.Iterator;
import java.util.List;
//this is the radius calculator class that is used to calculate the radius of the properties
//it is the baisic check for all search strategy
public class RadiusCalculator {
    
    // Calculate Manhattan distance between two addresses (using only street and avenue)
    private static int calculateManhattanDistance(int[] address1, int[] address2) {
        return (Math.abs(address1[0] - address2[0]) + Math.abs(address1[1] - address2[1]));
    }
    
// Filter out properties that are not within the given radius of the center address
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