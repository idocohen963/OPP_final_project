package src.factory;

/**
 * Abstract base class for all users in the real estate system.
 * Implements the Template Method pattern by defining the common structure
 * for all user types while allowing subclasses to implement specific behaviors.
 * 
 * The system supports three types of users:
 * - BUYER: Can view property information
 * - SELLER: Can view and delete properties (with observer notifications)
 * - BROKER: Can view and edit properties, receives deletion notifications
 * 
 * Each user has a unique ID and a specific type that determines their permissions.
 * 
 * @see src.factory.UserFactory
 * @see src.factory.Buyer
 * @see src.factory.Seller  
 * @see src.factory.Broker
 */
public abstract class User {
    /**
     * Enumeration defining the types of users in the system.
     * Each type has different permissions and capabilities.
     */
    public enum UserType {
        /** Buyer - can view properties */
        BUYER,
        /** Seller - can view and delete properties */
        SELLER,
        /** Broker - can view and edit properties, receives notifications */
        BROKER
    }

    /** The unique identifier for this user */
    protected final int userId;
    
    /** The type of user, determining their permissions */
    protected final UserType userType;
    
    /**
     * Protected constructor for creating a user.
     * Only accessible by subclasses and the UserFactory.
     * 
     * @param userId The unique identifier for the user (must be non-negative)
     * @param userType The type of user being created
     * @throws IllegalArgumentException if userId is negative
     */
    protected User(int userId, UserType userType) {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        this.userId = userId;
        this.userType = userType;
    }

    /**
     * Gets the unique identifier of this user.
     * 
     * @return The user's ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the type of this user.
     * 
     * @return The user's type (BUYER, SELLER, or BROKER)
     */
    public UserType getUserType() {
        return userType;
    }
    
} 
