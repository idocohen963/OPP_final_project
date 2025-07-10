package src.main;

import src.decorator.*;
import src.factory.Broker;
import src.factory.Buyer;
import src.factory.Seller;
import src.util.FileReaderProperty;
import src.util.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Central system manager for the Manhattan real estate broker system.
 * Implements the Singleton pattern to ensure only one instance manages the system.
 * 
 * This class is responsible for:
 * - Managing the collection of all properties in the system
 * - Coordinating property transactions with decorators for additional services
 * - Providing CRUD operations for properties
 * - Initializing the system with property data from files
 * 
 * The system supports various additional services through the Decorator pattern:
 * - Evening services
 * - Cleaning services
 * - Moving services
 * - Design services
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.decorator.Deal
 * @see src.util.Property
 * @see src.factory.User
 */
public class SystemManager {
    /** Single instance of SystemManager (Singleton pattern) */
    private static SystemManager instance = null;
    
    /** List to store all properties in the system */
    private final List<Property> properties;

    /**
     * Private constructor to prevent direct instantiation.
     * Initializes the properties list.
     */
    private SystemManager() {
        properties = new ArrayList<>();
    }
    
    /**
     * Returns the single instance of SystemManager (Singleton pattern).
     * Creates the instance if it doesn't exist.
     * 
     * @return The single SystemManager instance
     */
    public static SystemManager getInstance() {
        if (instance == null) {
            instance = new SystemManager();
        }
        return instance;
    }
    
    /**
     * Initializes the system with properties from a file.
     * Uses FileReaderProperty to read and parse property data.
     * 
     * @param filePath The path to the file containing property data
     * @throws RuntimeException if file reading fails
     * @see src.util.FileReaderProperty
     */
    public void initializeProperties(String filePath) {
        FileReaderProperty fileReader = new FileReaderProperty();
        properties.addAll(fileReader.readPropertiesFromFile(filePath));
    }
    
    /**
     * Returns a copy of all properties in the system.
     * 
     * @return A new list containing all properties
     */
    public List<Property> getAllProperties() {
        return new ArrayList<>(properties);
    }
   
    /**
     * Removes a property from the system by its address.
     * 
     * @param address The address coordinates of the property to remove
     * @return true if the property was found and removed, false otherwise
     * @throws IllegalArgumentException if address is null or has less than 2 coordinates
     */
    public boolean removeProperty(int[] address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        if (address.length < 2) {
            throw new IllegalArgumentException("Address must contain at least 2 coordinates");
        }   
        for (int i = 0; i < properties.size(); i++) {
            if (Arrays.equals(properties.get(i).getAddress(), address)) {
                properties.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Updates an existing property in the system.
     * 
     * @param address The current address of the property to update
     * @param updatedProperty The new property data
     * @return true if the property was found and updated, false otherwise
     * @throws IllegalArgumentException if address or updatedProperty is null,
     *                                  if address has less than 2 coordinates,
     *                                  or if the new address already exists
     */
    public boolean updateProperty(int[] address, Property updatedProperty) {
        if (address == null || updatedProperty == null) {
            throw new IllegalArgumentException("Address and updated property cannot be null");
        }
        if  (address.length < 2) {
            throw new IllegalArgumentException("Address must contain at least 2 coordinates");
        }
        // Check if the new address already exists (if address is being changed)
        if (!Arrays.equals(address, updatedProperty.getAddress())) {
            for (Property property : properties) {
                if (Arrays.equals(property.getAddress(), updatedProperty.getAddress())) {
                    throw new IllegalArgumentException("Cannot update: new address already exists");
                }
            }
        }
        
        // Find and update the property
        for (int i = 0; i < properties.size(); i++) {
            if (Arrays.equals(properties.get(i).getAddress(), address)) {
                properties.set(i, updatedProperty);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Executes a complete property deal with optional additional services.
     * Uses the Decorator pattern to add services to the base deal.
     * 
     * Supported services:
     * - "EVENING": Evening services (+$1000)
     * - "CLEANING": Cleaning services (+$2000)
     * - "MOVING": Moving services (+$3000)
     * - "DESIGN": Design services (+$4000)
     * 
     * @param property The property being sold
     * @param services List of additional services to include
     * @param seller The seller user
     * @param buyer The buyer user
     * @param broker The broker managing the transaction
     * @throws IllegalStateException if the property is already sold
     * @throws IllegalArgumentException if an unknown service is requested
     */
    public void executeWholeDeal(Property property, List<String> services, Seller seller, Buyer buyer, Broker broker) {
        // Check if property is already sold
        if (property.getStatus()) {
            throw new IllegalStateException("Property is already sold");
        }

        // Create base deal and apply decorator pattern for additional services
        Deal deal = new BasicDeal(property, buyer, seller, broker);
        System.out.println("broker: did you want to add any of the following services?\nEveningServices\nCleaning\nMoving\nDesign");
        System.out.println("buyer: yes, I want to add services:");
        if (services.isEmpty()) {
            System.out.println("No services needed.");
        } else {
            services.forEach(service -> System.out.print(service + "\n"));
        }
        if (services.isEmpty()) {
            deal.executeDeal();
            System.out.println("Total price: " + deal.getTotalPrice()); 
            return;
        }
        
        // Apply decorators based on requested services
        for (String service : services) {
            switch (service.toUpperCase()) {
                case "EVENING" -> deal = new EveningServicesDecorator(deal);
                case "CLEANING" -> deal = new CleaningDecorator(deal);
                case "MOVING" -> deal = new MovingDecorator(deal);
                case "DESIGN" -> deal = new DesignDecorator(deal);
                default -> throw new IllegalArgumentException("Unknown service: " + service);
            }
        }
        deal.executeDeal();
            System.out.println("Total price: " + deal.getTotalPrice()); 
    }
} 