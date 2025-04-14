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

public class SystemManager {
    // Single instance of src.main.SystemManager
    private static SystemManager instance = null;
    
    // List to store all properties
    private final List<Property> properties;

    // Private constructor
    private SystemManager() {
        properties = new ArrayList<>();
    }
    
    // Get instance method
    public static SystemManager getInstance() {
        if (instance == null) {
            instance = new SystemManager();
        }
        return instance;
    }
    
    // Initialize properties list
    public void initializeProperties(String filePath) {
        FileReaderProperty fileReader = new FileReaderProperty();
        properties.addAll(fileReader.readPropertiesFromFile(filePath));
    }
    
    // Get all properties
    public List<Property> getAllProperties() {
        return new ArrayList<>(properties);
    }
   
    // Remove property by address
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
    
    // Update existing property
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
    
    // deal with all services if addedm
    public void executeWholeDeal(Property property, List<String> services, Seller seller, Buyer buyer, Broker broker) {
        // Check if property is already sold
        if (property.getStatus()) {
            throw new IllegalStateException("src.util.Property is already sold");
        }

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