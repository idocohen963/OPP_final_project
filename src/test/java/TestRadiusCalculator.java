package src.test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.main.SystemManager;
import src.util.Property;
import src.util.RadiusCalculator;

import java.util.List;

import static org.junit.Assert.*;
// good


public class TestRadiusCalculator {
    private SystemManager systemManager;
    
    @Before
    public void setUp() {
        systemManager = SystemManager.getInstance();
        systemManager.initializeProperties("src/main/prop1.txt");
    }
    
    // Test to check if properties are returned correctly within a valid radius
    @Test
    public void testGetPropertiesInRadius_ValidInput() {
        int[] centerAddress = {4, 5};
        int radius = 2;
        List<Property> properties = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        assertTrue(!properties.isEmpty());
        
        // All properties in result should be within radius
        for (Property property : properties) {
            int[] propertyAddress = property.getAddress();
            int distance = Math.abs(propertyAddress[0] - centerAddress[0]) + 
                         Math.abs(propertyAddress[1] - centerAddress[1]);
            assertTrue(distance <= radius);
        }
    }
    
    // Test to check if only properties at the exact center location are returned when radius is zero
    @Test
    public void testGetPropertiesInRadius_ZeroRadius() {
        int[] centerAddress = {4, 5};
        int radius = 0;
        List<Property> properties = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        assertTrue(!properties.isEmpty());
        
        // Should only include properties at exact center location
        for (Property property : properties) {
            int[] propertyAddress = property.getAddress();
            assertEquals(centerAddress[0], propertyAddress[0]);
            assertEquals(centerAddress[1], propertyAddress[1]);
        }
    }
    
    // Test to validate that appropriate exceptions are thrown for invalid inputs
    @Test
    public void testGetPropertiesInRadius_InvalidInputs() {
        // Test null center address
        assertThrows(IllegalArgumentException.class, () -> 
            RadiusCalculator.getPropertiesInRadius(null, 1));
            
        // Test invalid center address length
        assertThrows(IllegalArgumentException.class, () -> 
            RadiusCalculator.getPropertiesInRadius(new int[]{1}, 1));
            
        // Test negative radius
        assertThrows(IllegalArgumentException.class, () -> 
            RadiusCalculator.getPropertiesInRadius(new int[]{4, 5}, -1));
    }
    
    // Test to check if all properties are returned when the radius is larger than any distance in test data
    @Test
    public void testGetPropertiesInRadius_LargeRadius() {
        int[] centerAddress = {4, 5};
        int radius = 100;  // Larger than any distance in test data
        List<Property> properties = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        assertTrue(!properties.isEmpty());
        
        // Should include all properties in the system
        Assert.assertEquals(systemManager.getAllProperties().size(), properties.size());
    }
    
    // Test to check that no properties are returned when the center address is far from any test properties
    @Test
    public void testGetPropertiesInRadius_NoPropertiesInRange() {
        int[] centerAddress = {100, 100};  // Far from any test properties
        int radius = 1;
        List<Property> properties = RadiusCalculator.getPropertiesInRadius(centerAddress, radius);
        // Assert that the properties list is empty
        assertTrue(properties.isEmpty());
    }
} 