package src.strategy;

import java.util.Iterator;
import java.util.List;
import src.util.Property;
import src.util.RadiusCalculator;
//this is the search by status strategy class that is used to search for properties by status (sold or available)
public class SearchByStatusStrategy implements PropertySearchStrategy<List<Property>> {
    private final boolean searchForSold;  // true for sold, false for available

    //constructor for the search by status strategy depends on the searchForSold
    public SearchByStatusStrategy(boolean searchForSold) {
        this.searchForSold = searchForSold;
    }

    @Override
    public List<Property> search(int[] centerAddress, int radius) {
        if (centerAddress == null || centerAddress.length < 2) {
            throw new IllegalArgumentException("Center address must contain at least street and avenue coordinates");
        }
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        //get the properties in the radius
        List<Property> propertiesInRadius = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        //iterator for the properties in the radius
        Iterator<Property> iterator = propertiesInRadius.iterator();
        while (iterator.hasNext()) {
            Property property = iterator.next();
            if (property.getStatus() != searchForSold) {
                iterator.remove();
            }
        }
        //return the properties in the radius that are sold or available
        return propertiesInRadius;
    }
} 