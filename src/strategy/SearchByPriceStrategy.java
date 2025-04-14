package src.strategy;

import java.util.Iterator;
import java.util.List;
import src.util.Property;
import src.util.RadiusCalculator;
//this is the search by price strategy class that is used to search for properties by price
public class SearchByPriceStrategy implements PropertySearchStrategy<List<Property>> {
    public enum PriceComparison {
        HIGHER,
        LOWER,
        EQUAL
    }

    private final double targetPrice;
    private final PriceComparison comparison;

    //constructor for the search by price strategy depends on the target price
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
//
    @Override
    public List<Property> search(int[] centerAddress, int radius) {
        if (centerAddress == null || centerAddress.length < 2) {
            throw new IllegalArgumentException("Center address must contain at least street and avenue coordinates");
        }
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
    
        List<Property> propertiesInRadius = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        
        Iterator<Property> iterator = propertiesInRadius.iterator();
        while (iterator.hasNext()) {
            Property property = iterator.next();
            double propertyPrice = property.getTotalPrice();
            
            boolean shouldRemove = switch (comparison) {
                case HIGHER -> propertyPrice <= targetPrice;
                case LOWER -> propertyPrice >= targetPrice;
                case EQUAL -> propertyPrice != targetPrice;
            };

            if (shouldRemove) {
                iterator.remove();
            }
        }
        //return the properties in the radius that is higher or lower or equal to the target price
        return propertiesInRadius;
    }
} 