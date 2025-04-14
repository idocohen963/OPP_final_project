package src.test.java;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import src.util.Property;

//good
public class TestProperty {
    
    // Tests for valid property creation
    @Test
    public void testValidPropertyCreation() {
        int[] address = {5, 12};
        Property prop = new Property(address, 80.5, 10000, false);
        
        Assert.assertArrayEquals(address, prop.getAddress());
        Assert.assertEquals(80.5, prop.getArea(), 0.001);
        Assert.assertEquals(10000, prop.getPricePerSquareMeter(), 0.001);
        assertFalse(prop.getStatus());
    }

    // Tests for invalid fields
    @Test
    public void testInvalidAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Property(new int[]{5}, 100, 5000, false);
        });
    }

    @Test
    public void testNegativeArea() {
        int[] address = {3, 4};
        assertThrows(IllegalArgumentException.class, () -> {
            new Property(address, -50, 7000, true);
        });
    }

    // Test for price calculation
    @Test
    public void testTotalPriceCalculation() {
        Property prop = new Property(new int[]{2,7}, 120, 8500, false);
        Assert.assertEquals(120 * 8500, prop.getTotalPrice(), 0.001);

    }

    // Test for status change
    @Test
    public void testStatusChange() {
        Property prop = new Property(new int[]{1,1}, 90, 6000, false);
        prop.setStatus(true);
        assertTrue(prop.getStatus());
    }

    // Test for address copy protection
    @Test
    public void testAddressCopyProtection() {
        int[] original = {8, 3};
        Property prop = new Property(original, 200, 3000, true);
        original[0] = 999; // Change in original should not affect the object
        
        Assert.assertArrayEquals(new int[]{8,3}, prop.getAddress());
    }

    // Test for toString
    @Test
    public void testToStringFormat() {
        Property prop = new Property(new int[]{6,9}, 75.5, 12000, true);
        String expected = "address: [6, 9] area: 75.5 pricePerSquareMeter: 12000.0 status: true";
        Assert.assertEquals(expected, prop.toString());
    }


}