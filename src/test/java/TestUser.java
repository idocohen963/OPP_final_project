package src.test.java;

import org.junit.Assert;
import org.junit.Test;
import src.factory.*;
import src.permission.DeletePermission;
import src.permission.EditPermission;
import src.permission.ViewPermission;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Comprehensive unit tests for the User creation and permission system.
 * Tests the Factory pattern implementation and permission assignments.
 * 
 * Test coverage includes:
 * - Valid user creation for all types
 * - Invalid input validation
 * - Duplicate user prevention
 * - Permission assignment verification
 * - User type classification
 * - Factory pattern functionality
 * 
 * @see src.factory.UserFactory
 * @see src.factory.User
 * @see src.permission.ViewPermission
 * @see src.permission.EditPermission
 * @see src.permission.DeletePermission
 */
public class TestUser {
    /**
     * Tests creation of valid users for all user types.
     * Verifies that user ID and type are correctly set.
     */
    @Test
    public void testUserCreationWithValidId() {
        User buyer = UserFactory.createUser(User.UserType.BUYER, 123);
        Assert.assertEquals(123, buyer.getUserId());
        Assert.assertEquals(User.UserType.BUYER, buyer.getUserType());
        
        User seller = UserFactory.createUser(User.UserType.SELLER, 456);
        Assert.assertEquals(456, seller.getUserId());
        Assert.assertEquals(User.UserType.SELLER, seller.getUserType());
        
        User broker = UserFactory.createUser(User.UserType.BROKER, 789);
        Assert.assertEquals(789, broker.getUserId());
        Assert.assertEquals(User.UserType.BROKER, broker.getUserType());
    }

    /**
     * Tests that negative user ID throws IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserCreationWithNegativeId() {
        UserFactory.createUser(User.UserType.BUYER, -1);
    }

    /**
     * Tests that creating duplicate buyers throws IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateBuyerCreation() {
        UserFactory.createUser(User.UserType.BUYER, 111);
        UserFactory.createUser(User.UserType.BUYER, 111);
    }

    /**
     * Tests that creating duplicate sellers throws IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateSellerCreation() {
        UserFactory.createUser(User.UserType.SELLER, 222);
        UserFactory.createUser(User.UserType.SELLER, 222);
    }

    /**
     * Tests that creating duplicate brokers throws IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateBrokerCreation() {
        UserFactory.createUser(User.UserType.BROKER, 333);
        UserFactory.createUser(User.UserType.BROKER, 333);
    }

    /**
     * Tests that each user type has the correct permissions.
     * Verifies the permission system implementation.
     */
    @Test
    public void testUserTypePermissions() {
        // Test Buyer permissions - View only
        User buyer = UserFactory.createUser(User.UserType.BUYER, 111111);
        assertTrue(buyer instanceof ViewPermission);
        assertFalse(buyer instanceof DeletePermission);
        assertFalse(buyer instanceof EditPermission);

        // Test Seller permissions - View and Delete
        User seller = UserFactory.createUser(User.UserType.SELLER, 111112);
        assertTrue(seller instanceof ViewPermission);
        assertTrue(seller instanceof DeletePermission);
        assertFalse(seller instanceof EditPermission);

        // Test Broker permissions - View and Edit
        User broker = UserFactory.createUser(User.UserType.BROKER, 111113);
        assertTrue(broker instanceof ViewPermission);
        assertFalse(broker instanceof DeletePermission);
        assertTrue(broker instanceof EditPermission);
    }

    /**
     * Tests that users are created as the correct concrete type.
     * Verifies proper inheritance and type classification.
     */
    @Test
    public void testUserTypeClassification() {
        User buyer = UserFactory.createUser(User.UserType.BUYER, 111114);
        assertTrue(buyer instanceof Buyer);
        assertFalse(buyer instanceof Seller);
        assertFalse(buyer instanceof Broker);

        User seller = UserFactory.createUser(User.UserType.SELLER, 111115);
        assertFalse(seller instanceof Buyer);
        assertTrue(seller instanceof Seller);
        assertFalse(seller instanceof Broker);

        User broker = UserFactory.createUser(User.UserType.BROKER, 111116);
        assertFalse(broker instanceof Buyer);
        assertFalse(broker instanceof Seller);
        assertTrue(broker instanceof Broker);
    }
}
