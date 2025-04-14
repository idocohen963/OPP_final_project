package src.test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.main.SystemManager;
import src.strategy.SearchByAveragePriceStrategy;
import src.strategy.SearchByPriceStrategy;
import src.strategy.SearchByStatusStrategy;
import src.util.Property;
import src.util.RadiusCalculator;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//good
public class TestSearchStrategies {
    private SystemManager systemManager;
    private int[] centerAddress;
    private int radius;

    @Before
    public void setUp() {
        systemManager = SystemManager.getInstance();
        systemManager.initializeProperties("src/main/prop1.txt");
        centerAddress = new int[]{4, 5};  // Center address with several properties around it according to prop1.txt
        radius = 2;
    }

    // Test to check if the average price is greater than 0
    @Test
    public void testSearchByAveragePrice() {
        SearchByAveragePriceStrategy strategy = new SearchByAveragePriceStrategy();
        double avgPrice = strategy.search(centerAddress, radius);
        assertTrue(avgPrice > 0);
        
        // Edge case test - radius 0
        double avgPriceZeroRadius = strategy.search(centerAddress, 0);
        assertTrue(avgPriceZeroRadius >= 0);
    }

    // Test to ensure all found properties are marked as sold
    @Test
    public void testSearchByStatusSold() {
        SearchByStatusStrategy strategy = new SearchByStatusStrategy(true);
        List<Property> soldProperties = strategy.search(centerAddress, radius);
        
        for (Property p : soldProperties) {
            assertTrue(p.getStatus());  // Ensure all found properties are marked as sold
            assertTrue(RadiusCalculator.getPropertiesInRadius(centerAddress, radius).contains(p));
        }
    }

    // Test to ensure all found properties are available for sale
    @Test
    public void testSearchByStatusAvailable() {
        SearchByStatusStrategy strategy = new SearchByStatusStrategy(false);
        List<Property> availableProperties = strategy.search(centerAddress, radius);
        
        for (Property p : availableProperties) {
            assertFalse(p.getStatus());  // Ensure all found properties are available for sale
            assertTrue(RadiusCalculator.getPropertiesInRadius(centerAddress, radius).contains(p));
        }
    }

    // Test to check if all found properties have a total price higher than the target price
    @Test
    public void testSearchByPriceHigher() {
        double targetPrice = 100000;  // Relatively high price according to the data in prop1.txt
        SearchByPriceStrategy strategy = new SearchByPriceStrategy(targetPrice, SearchByPriceStrategy.PriceComparison.HIGHER);
        List<Property> properties = strategy.search(centerAddress, radius);
        
        for (Property p : properties) {
            assertTrue(p.getTotalPrice() > targetPrice);
            assertTrue(RadiusCalculator.getPropertiesInRadius(centerAddress, radius).contains(p));
        }
    }

    // Test to check if all found properties have a total price lower than the target price
    @Test
    public void testSearchByPriceLower() {
        double targetPrice = 1000000;  // Very high price that most properties will be below
        SearchByPriceStrategy strategy = new SearchByPriceStrategy(targetPrice, SearchByPriceStrategy.PriceComparison.LOWER);
        List<Property> properties = strategy.search(centerAddress, radius);
        
        for (Property p : properties) {
            assertTrue(p.getTotalPrice() < targetPrice);
            assertTrue(RadiusCalculator.getPropertiesInRadius(centerAddress, radius).contains(p));
        }
    }

    // Test to ensure that the found property matches the target price
    @Test
    public void testSearchByPriceEqual() {
        Property firstProperty = systemManager.getAllProperties().get(0);
        double targetPrice = firstProperty.getTotalPrice();
        SearchByPriceStrategy strategy = new SearchByPriceStrategy(targetPrice, SearchByPriceStrategy.PriceComparison.EQUAL);
        List<Property> properties = strategy.search(new int[]{firstProperty.getAddress()[0], firstProperty.getAddress()[1]}, 0);
        
        assertFalse(properties.isEmpty());
        Assert.assertEquals(targetPrice, properties.get(0).getTotalPrice(), 0);
    }

    // Test to check if an exception is thrown for an invalid radius
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRadius() {
        SearchByAveragePriceStrategy strategy = new SearchByAveragePriceStrategy();
        strategy.search(centerAddress, -1);
    }

    // Test to check if an exception is thrown for an invalid center address
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCenterAddress() {
        SearchByAveragePriceStrategy strategy = new SearchByAveragePriceStrategy();
        strategy.search(new int[]{1}, radius);  // Invalid address - must contain at least 2 coordinates
    }

    // Test to check if an exception is thrown for a null center address
    @Test(expected = IllegalArgumentException.class)
    public void testNullCenterAddress() {
        SearchByAveragePriceStrategy strategy = new SearchByAveragePriceStrategy();
        strategy.search(null, radius);
    }
} 