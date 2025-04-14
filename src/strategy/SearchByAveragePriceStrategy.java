package src.strategy;

import java.util.List;
import src.util.Property;
import src.util.RadiusCalculator;

public class SearchByAveragePriceStrategy implements PropertySearchStrategy<Double> {
    
    @Override
    public Double search(int[] centerAddress, int radius) {
        if (centerAddress == null || centerAddress.length < 2) {
            throw new IllegalArgumentException("Center address must contain at least street and avenue coordinates");
        }
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }

        List<Property> propertiesInRadius = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        
        if (propertiesInRadius.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Property property : propertiesInRadius) {
            sum += property.getTotalPrice();
        }
        
        return sum / propertiesInRadius.size();
    }
} 