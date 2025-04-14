package src.factory;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {
    //this is the user factory class that is used to create a user
    //3 types of users: broker, seller, buyer
    private static final List<Broker> brokers = new ArrayList<>();
    private static final List<Seller> sellers = new ArrayList<>();
    private static final List<Buyer> buyers = new ArrayList<>();
    //create a user
    public static User createUser(User.UserType type, int userId) {
        return switch (type) {
            case BROKER -> createBroker(userId);
            case SELLER -> createSeller(userId);
            case BUYER -> createBuyer(userId);
        };
    }
    //create a broker
    private static Broker createBroker(int userId) {
        for (Broker broker : brokers) {
            if (broker.getUserId() == userId) {
                throw new IllegalArgumentException("src.factory.Broker with ID " + userId + " already exists.");
            }
        }
        Broker broker = new Broker(userId);
        brokers.add(broker);
        System.out.println("src.factory.Broker created with ID: " + broker.getUserId());
        return broker;
    }

    private static Seller createSeller(int userId) {
        for (Seller seller : sellers) {
            if (seller.getUserId() == userId) {
                throw new IllegalArgumentException("src.factory.Seller with ID " + userId + " already exists.");
            }
        }
            
        Seller seller = new Seller(userId);
        sellers.add(seller);
        System.out.println("src.factory.Seller created with ID: " + seller.getUserId());
        return seller;
    }
    
    private static Buyer createBuyer(int userId) {
        for (Buyer buyer : buyers) {
            if (buyer.getUserId() == userId) {
                throw new IllegalArgumentException("src.factory.Buyer with ID " + userId + " already exists.");
            }
        }
        Buyer buyer = new Buyer(userId);
        buyers.add(buyer);
        System.out.println("src.factory.Buyer created with ID: " + buyer.getUserId());
        return buyer;
    }
    
    // Setup observer relationships
    public static void setupObserver() {
        sellers.forEach(seller -> 
            brokers.forEach(broker -> 
                seller.setObserver(broker)));
    }
} 