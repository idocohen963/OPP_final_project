package src.test.java;

import org.junit.Before;
import org.junit.Test;
import src.decorator.*;
import src.factory.*;
import src.main.SystemManager;
import src.util.Property;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDealDecorators {
    private SystemManager systemManager;
    private Property property;
    private Seller seller;
    private Buyer buyer;
    private Broker broker;
    private Deal baseDeal;
    private static int nextUserId = 1; // Counter for generating unique user IDs

    @Before
    public void setUp() {
        systemManager = SystemManager.getInstance();
        systemManager.initializeProperties("src/main/prop1.txt");
        property = systemManager.getAllProperties().get(0);
        property.setStatus(false); // Set the property status to false (not sold)

        // Use unique IDs for each test
        seller = (Seller) UserFactory.createUser(User.UserType.SELLER, nextUserId++);
        buyer = (Buyer) UserFactory.createUser(User.UserType.BUYER, nextUserId++);
        broker = (Broker) UserFactory.createUser(User.UserType.BROKER, nextUserId++);
        
        baseDeal = new BasicDeal(property, buyer, seller, broker);
    }

    // Test to verify that the base deal price matches the property's total price
    @Test
    public void testBaseDeal() {
        assertEquals(property.getTotalPrice(), baseDeal.getTotalPrice(), 0.001);
    }

    // Test to check the total price after adding cleaning services to the deal
    @Test
    public void testCleaningDecorator() {
        List<String> services = new ArrayList<>();
        services.add("CLEANING");
        systemManager.executeWholeDeal(property, services, seller, buyer, broker);
        Deal withCleaning = new CleaningDecorator(baseDeal);
        assertEquals(baseDeal.getTotalPrice() + 2000, withCleaning.getTotalPrice(), 0.001);
    }

    // Test to check the total price after adding moving services to the deal
    @Test
    public void testMovingDecorator() {
        List<String> services = new ArrayList<>();
        services.add("MOVING");
        systemManager.executeWholeDeal(property, services, seller, buyer, broker);
        Deal withMoving = new MovingDecorator(baseDeal);
        assertEquals(baseDeal.getTotalPrice() + 3000, withMoving.getTotalPrice(), 0.001);
    }

    // Test to check the total price after adding design services to the deal
    @Test
    public void testDesignDecorator() {
        List<String> services = new ArrayList<>();
        services.add("DESIGN");
        systemManager.executeWholeDeal(property, services, seller, buyer, broker);
        Deal withDesign = new DesignDecorator(baseDeal);
        assertEquals(baseDeal.getTotalPrice() + 4000, withDesign.getTotalPrice(), 0.001);
    }

    // Test to check the total price after adding evening services to the deal
    @Test
    public void testEveningDecorator() {
        List<String> services = new ArrayList<>();
        services.add("EVENING");
        systemManager.executeWholeDeal(property, services, seller, buyer, broker);
        Deal withEvening = new EveningServicesDecorator(baseDeal);
        assertEquals(baseDeal.getTotalPrice() + 1000, withEvening.getTotalPrice(), 0.001);
    }

    // Test to check the total price after adding multiple services to the deal
    @Test
    public void testMultipleServicesOrder() {
        List<String> services = new ArrayList<>();
        services.add("CLEANING");
        services.add("MOVING");
        services.add("DESIGN");
        
        Deal deal = baseDeal;
        deal = new CleaningDecorator(deal);
        deal = new MovingDecorator(deal);
        deal = new DesignDecorator(deal);
        
        double expectedTotal = property.getTotalPrice() + 2000 + 3000 + 4000;
        assertEquals(expectedTotal, deal.getTotalPrice(), 0.001);
        
        systemManager.executeWholeDeal(property, services, seller, buyer, broker);
        assertTrue(property.getStatus()); // Verify property is marked as sold
    }

    // Test to ensure that an invalid service throws an IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidService() {
        List<String> services = new ArrayList<>();
        services.add("INVALID_SERVICE");
        systemManager.executeWholeDeal(property, services, seller, buyer, broker);
    }

    // New tests for the updated functionality
    @Test(expected = IllegalStateException.class)
    public void testDealWithSoldProperty() {
        // First deal - should succeed
        List<String> services = new ArrayList<>();
        systemManager.executeWholeDeal(property, services, seller, buyer, broker);
        
        // Second deal with the same property - should throw IllegalStateException
        systemManager.executeWholeDeal(property, services, seller, buyer, broker);
    }
} 