package src.factory;

import src.permission.ViewPermission;

/**
 * Represents a buyer in the real estate system.
 * Buyers have view permissions only - they can view property details
 * but cannot modify or delete properties.
 * 
 * This class implements the ViewPermission interface which provides
 * default implementations for viewing individual properties and
 * viewing all properties in the system.
 * 
 * Buyer capabilities:
 * - View individual property details by address
 * - View all properties in the system
 * - Participate in property deals as the purchasing party
 * 
 * @see src.factory.User
 * @see src.permission.ViewPermission
 */
public class Buyer extends User implements ViewPermission {
    
    /**
     * Constructs a new Buyer with the specified user ID.
     * 
     * @param userId The unique identifier for this buyer
     */
    public Buyer(int userId) {
        super(userId, User.UserType.BUYER);
    }
    
    // Note: ViewPermission interface provides default implementations
    // for viewProperty() and viewAllProperties() methods
}   
