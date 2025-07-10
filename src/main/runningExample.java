package src.main;

import src.factory.*;
import src.strategy.SearchByAveragePriceStrategy;
import src.strategy.SearchByStatusStrategy;
import src.util.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Main demonstration class for the Manhattan Real Estate System.
 * This class provides a comprehensive example of how to use the system's
 * various features and design patterns.
 * 
 * The example demonstrates:
 * - System initialization and property loading
 * - User creation using Factory pattern
 * - Observer pattern with property deletion notifications
 * - Strategy pattern for different search types
 * - Decorator pattern for adding services to deals
 * - Permission system for different user types
 * 
 * Design patterns showcased:
 * - Singleton: SystemManager
 * - Factory: UserFactory for creating users
 * - Observer: Property deletion notifications
 * - Strategy: Different search algorithms
 * - Decorator: Adding services to deals
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.main.SystemManager
 * @see src.factory.UserFactory
 * @see src.strategy.PropertySearchStrategy
 * @see src.decorator.Deal
 */
public class runningExample {

    /**
     * Main method demonstrating the real estate system functionality.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("welcome to my project this is the running example");
        
        // Initialize system using Singleton pattern
        SystemManager systemManager = SystemManager.getInstance();
        // Load properties from file
        systemManager.initializeProperties("src/main/prop1.txt");
        
        // Create users using Factory pattern
        Seller seller1 = (Seller) UserFactory.createUser(User.UserType.SELLER, 1);
        Seller seller2 = (Seller) UserFactory.createUser(User.UserType.SELLER, 2);
        Broker broker1 = (Broker) UserFactory.createUser(User.UserType.BROKER, 777);
        Broker broker2 = (Broker) UserFactory.createUser(User.UserType.BROKER, 888);
        Buyer buyer1 = (Buyer) UserFactory.createUser(User.UserType.BUYER, 55);
        Buyer buyer2 = (Buyer) UserFactory.createUser(User.UserType.BUYER, 66);
        
        // Set up Observer pattern relationships
        UserFactory.setupObserver();
        
        // Display all properties in the system
        System.out.println("\nlist of all Properties in the system:");
        for (Property property : systemManager.getAllProperties()) {
            System.out.println(property.toString()); 
        }

        // Demonstrate Observer pattern: seller deletes property, brokers get notified
        System.out.println("\nThe seller will send a message to the brokers (observer) when the property is sold.");
        seller1.deleteProperty(systemManager.getAllProperties().get(2).getAddress());
        
        // Show properties after deletion
        System.out.println("\nlist of all Properties in the system after deletion:");
        for (Property property : systemManager.getAllProperties()){
            System.out.println(property.toString()); 
        }

        // Demonstrate ViewPermission: buyer views property details
        System.out.println("\nthe buyer can see all the property details,for example the 2nd property ");
        buyer1.viewProperty(systemManager.getAllProperties().get(1).getAddress());  
        
        // Demonstrate EditPermission: broker edits property
        Property updatedProperty = new Property(new int[]{4,5,1,1}, 80, 10000, false);
        broker1.editProperty(systemManager.getAllProperties().get(0).getAddress(), updatedProperty);
        
        // Show properties after editing
        System.out.println("\nlist of all Properties in the system after editing the first property:");
        for (Property property : systemManager.getAllProperties()){
            System.out.println(property.toString()); 
        }
        System.out.println("\n");

        // Demonstrate Strategy pattern: search by average price
        SearchByAveragePriceStrategy averagePriceStrategy = new SearchByAveragePriceStrategy();
        int[] centerAddress = {4, 5}; 
        int radius = 8;
        double averagePrice = averagePriceStrategy.search(centerAddress, radius);
        System.out.println("Average price of properties in the radius: " + averagePrice+ "\n");

        // Demonstrate Strategy pattern: search by status
        SearchByStatusStrategy searchByStatusStrategy = new SearchByStatusStrategy(true); // true for sold
        List<Property> soldProperties = searchByStatusStrategy.search(centerAddress, radius);
        System.out.println("\nList of sold properties in the radius:");
        for (Property property : soldProperties) {
            System.out.println(property.toString());
        }
        System.out.println("\n");

        // Demonstrate Decorator pattern: execute deal with no services
        System.out.println("\nexecute deal with no services");
        List<String> services = new ArrayList<>();
        systemManager.executeWholeDeal(systemManager.getAllProperties().get(0), services, seller1, buyer1, broker1);
        System.out.println("\n");

        // Demonstrate Decorator pattern: execute deal with multiple services
        services.add("evening");
        services.add("cleaning");
        System.out.println("\nexecute deal with services");
        systemManager.executeWholeDeal(systemManager.getAllProperties().get(2), services, seller2, buyer2, broker2);

        // Show that properties are now sold
        System.out.println("\nthe property's status is true after the deals");
        System.out.println(systemManager.getAllProperties().get(0).toString());
        System.out.println(systemManager.getAllProperties().get(2).toString());
    }   
}
