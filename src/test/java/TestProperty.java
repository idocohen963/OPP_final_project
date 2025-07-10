package src.test.java;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import src.util.Property;

/**
 * Comprehensive unit tests for the Property class.
 * Tests all aspects of property creation, validation, and functionality.
 * 
 * Test coverage includes:
 * - Valid property creation with various address formats
 * - Invalid input validation and exception handling
 * - Price calculation accuracy
 * - Status change functionality
 * - Address immutability protection
 * - String representation formatting
 * 
 * @see src.util.Property
 */
public class TestProperty {
    /**
     * Tests creation of a valid property with standard parameters.
     * Verifies that all property attributes are correctly set.
     */
    @Test
    public void testValidPropertyCreation() {
        int[] address = {5, 12};
        Property prop = new Property(address, 80.5, 10000, false);
        
        Assert.assertArrayEquals(address, prop.getAddress());
        Assert.assertEquals(80.5, prop.getArea(), 0.001);
        Assert.assertEquals(10000, prop.getPricePerSquareMeter(), 0.001);
        assertFalse(prop.getStatus());
    }

    /**
     * Tests that invalid address (less than 2 coordinates) throws exception.
     */
    @Test
    public void testInvalidAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Property(new int[]{5}, 100, 5000, false);
        });
    }

    /**
     * Tests that negative area throws IllegalArgumentException.
     */
    @Test
    public void testNegativeArea() {
        int[] address = {3, 4};
        assertThrows(IllegalArgumentException.class, () -> {
            new Property(address, -50, 7000, true);
        });
    }

    /**
     * Tests total price calculation (area * price per square meter).
     */
    @Test
    public void testTotalPriceCalculation() {
        Property prop = new Property(new int[]{2,7}, 120, 8500, false);
        Assert.assertEquals(120 * 8500, prop.getTotalPrice(), 0.001);
    }

    /**
     * Tests that property status can be changed correctly.
     */
    @Test
    public void testStatusChange() {
        Property prop = new Property(new int[]{1,1}, 90, 6000, false);
        prop.setStatus(true);
        assertTrue(prop.getStatus());
    }

    /**
     * Tests that modifying the original address array doesn't affect the property.
     * Verifies immutability protection.
     */
    @Test
    public void testAddressCopyProtection() {
        int[] original = {8, 3};
        Property prop = new Property(original, 200, 3000, true);
        original[0] = 999; // Change in original should not affect the object
        
        Assert.assertArrayEquals(new int[]{8,3}, prop.getAddress());
    }

    /**
     * Tests the string representation format of the property.
     */
    @Test
    public void testToStringFormat() {
        Property prop = new Property(new int[]{6,9}, 75.5, 12000, true);
        String expected = "address: [6, 9] area: 75.5 pricePerSquareMeter: 12000.0 status: true";
        Assert.assertEquals(expected, prop.toString());
    }


}
