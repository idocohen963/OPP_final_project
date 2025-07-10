package src.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class for creating users in the real estate system.
 * Implements the Factory Method pattern to centralize user creation
 * and ensure proper initialization and validation.
 * 
 * This factory:
 * - Creates users of different types (Buyer, Seller, Broker)
 * - Maintains collections of each user type
 * - Prevents duplicate user IDs within each type
 * - Sets up observer relationships between sellers and brokers
 * 
 * The factory ensures that:
 * - Each user type has unique IDs within their category
 * - Proper observer patterns are established
 * - Users are properly initialized with their permissions
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.factory.User
 * @see src.factory.Buyer
 * @see src.factory.Seller
 * @see src.factory.Broker
 */
public class UserFactory {
    /** Collection of all brokers in the system */
    private static final List<Broker> brokers = new ArrayList<>();
    
    /** Collection of all sellers in the system */
    private static final List<Seller> sellers = new ArrayList<>();
    
    /** Collection of all buyers in the system */
    private static final List<Buyer> buyers = new ArrayList<>();
    
    /**
     * Creates a user of the specified type with the given ID.
     * This is the main factory method that delegates to specific creation methods.
     * 
     * @param type The type of user to create (BUYER, SELLER, or BROKER)
     * @param userId The unique identifier for the user
     * @return The created user instance
     * @throws IllegalArgumentException if a user with the same ID already exists
     *                                  within the same type, or if userId is negative
     */
    public static User createUser(User.UserType type, int userId) {
        return switch (type) {
            case BROKER -> createBroker(userId);
            case SELLER -> createSeller(userId);
            case BUYER -> createBuyer(userId);
        };
    }
    
    /**
     * Creates a new broker with the specified ID.
     * 
     * @param userId The unique identifier for the broker
     * @return The created broker instance
     * @throws IllegalArgumentException if a broker with the same ID already exists
     */
    private static Broker createBroker(int userId) {
        for (Broker broker : brokers) {
            if (broker.getUserId() == userId) {
                throw new IllegalArgumentException("Broker with ID " + userId + " already exists.");
            }
        }
        Broker broker = new Broker(userId);
        brokers.add(broker);
        System.out.println("Broker created with ID: " + broker.getUserId());
        return broker;
    }

    /**
     * Creates a new seller with the specified ID.
     * 
     * @param userId The unique identifier for the seller
     * @return The created seller instance
     * @throws IllegalArgumentException if a seller with the same ID already exists
     */
    private static Seller createSeller(int userId) {
        for (Seller seller : sellers) {
            if (seller.getUserId() == userId) {
                throw new IllegalArgumentException("Seller with ID " + userId + " already exists.");
            }
        }
            
        Seller seller = new Seller(userId);
        sellers.add(seller);
        System.out.println("Seller created with ID: " + seller.getUserId());
        return seller;
    }
    
    /**
     * Creates a new buyer with the specified ID.
     * 
     * @param userId The unique identifier for the buyer
     * @return The created buyer instance
     * @throws IllegalArgumentException if a buyer with the same ID already exists
     */
    private static Buyer createBuyer(int userId) {
        for (Buyer buyer : buyers) {
            if (buyer.getUserId() == userId) {
                throw new IllegalArgumentException("Buyer with ID " + userId + " already exists.");
            }
        }
        Buyer buyer = new Buyer(userId);
        buyers.add(buyer);
        System.out.println("Buyer created with ID: " + buyer.getUserId());
        return buyer;
    }
    
    /**
     * Sets up observer relationships between sellers and brokers.
     * Each seller will notify all brokers when a property is deleted.
     * This method should be called after creating all users.
     * 
     * Implements the Observer pattern where:
     * - Sellers are subjects that notify when properties are deleted
     * - Brokers are observers that receive deletion notifications
     */
    public static void setupObserver() {
        sellers.forEach(seller -> 
            brokers.forEach(broker -> 
                seller.setObserver(broker)));
    }
} 