package src.main;

import src.factory.*;
import src.strategy.SearchByAveragePriceStrategy;
import src.strategy.SearchByStatusStrategy;
import src.util.Property;

import java.util.ArrayList;
import java.util.List;


public class runningExample {

    public static void main(String[] args) {
        System.out.println("welcome to my project this is the running example");
        //uploud the system using getInstance method (singleton)
        SystemManager systemManager = SystemManager.getInstance();
        //initialize the properties from the file prop1.txt
        systemManager.initializeProperties("src/main/prop1.txt");
        
        // Create users directly
        Seller seller1 = (Seller) UserFactory.createUser(User.UserType.SELLER, 1);
        Seller seller2 = (Seller) UserFactory.createUser(User.UserType.SELLER, 2);
        Broker broker1 = (Broker) UserFactory.createUser(User.UserType.BROKER, 777);
        Broker broker2 = (Broker) UserFactory.createUser(User.UserType.BROKER, 888);
        Buyer buyer1 = (Buyer) UserFactory.createUser(User.UserType.BUYER, 55);
        Buyer buyer2 = (Buyer) UserFactory.createUser(User.UserType.BUYER, 66);
        
        UserFactory.setupObserver();
        
        System.out.println("\nlist of all Properties in the system:");
        for (Property property : systemManager.getAllProperties()) {
            System.out.println(property.toString()); }

        //the seller (delete permission) delete the 3rd property in the list
        System.out.println("\nThe seller will send a message to the brokers (observer) when the property is sold.");
        seller1.deleteProperty(systemManager.getAllProperties().get(2).getAddress());
        //print the list of all properties in the system after deletion
        System.out.println("\nlist of all Properties in the system after deletion:");
        for (Property property : systemManager.getAllProperties()){
            System.out.println(property.toString()); }

        //the buyer (view permission) view the 2nd property in the list
        System.out.println("\nthe buyer can see all the property details,for example the 2nd property ");
        buyer1.viewProperty(systemManager.getAllProperties().get(1).getAddress());  
        
        //the broker (edit permission) edit the first property in the list
        Property updatedProperty=new Property(new int[]{4,5,1,1}, 80, 10000, false);
        broker1.editProperty(systemManager.getAllProperties().get(0).getAddress(), updatedProperty);
        //print the list of all properties in the system after editing
        System.out.println("\nlist of all Properties in the system after editing the first property:");
        for (Property property : systemManager.getAllProperties()){
            System.out.println(property.toString()); }
        System.out.println("\n");

        //search by average price
        SearchByAveragePriceStrategy averagePriceStrategy = new SearchByAveragePriceStrategy();
        int[] centerAddress = {4, 5}; 
        int radius = 8;
        double averagePrice = averagePriceStrategy.search(centerAddress, radius);
        System.out.println("Average price of properties in the radius: " + averagePrice+ "\n");

        //search by status
        SearchByStatusStrategy searchByStatusStrategy = new SearchByStatusStrategy(true); // true for sold
        List<Property> soldProperties = searchByStatusStrategy.search(centerAddress, radius);
        System.out.println("\nList of sold properties in the radius:");
        for (Property property : soldProperties) {
            System.out.println(property.toString());}
        System.out.println("\n");

        //execute deal with no services (arraylist is empty)
        //the users pick to the deal unsold property (status=fulse) for each execute
        System.out.println("\nexecute deal with no services");
        List<String> services = new ArrayList<>();
        systemManager.executeWholeDeal(systemManager.getAllProperties().get(0), services, seller1, buyer1, broker1);
        System.out.println("\n");

        //execute deal with services (evening and cleaning)
        services.add("evening");
        services.add("cleaning");
        System.out.println("\nexecute deal with no services");
        systemManager.executeWholeDeal(systemManager.getAllProperties().get(2), services, seller2, buyer2, broker2);

        //the property's is sold (status=true)
        System.out.println("\nthe property's status is true after the deals");
        System.out.println(systemManager.getAllProperties().get(0).toString());
        System.out.println(systemManager.getAllProperties().get(2).toString());



    }   
}
