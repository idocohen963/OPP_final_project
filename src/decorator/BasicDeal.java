package src.decorator;

import java.util.Arrays;
import src.factory.Broker;
import src.factory.User;
import src.util.Property;

/**
 * Basic implementation of a property deal without additional services.
 * This class represents the core deal functionality that can be extended
 * with additional services using the Decorator pattern.
 * 
 * A basic deal includes:
 * - The property being sold
 * - The buyer, seller, and managing broker
 * - Basic transaction processing
 * - Property status updates
 * 
 * This class serves as the base component in the Decorator pattern,
 * providing the fundamental deal functionality that decorators can enhance.
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.decorator.Deal
 * @see src.decorator.DealDecorator
 */
public class BasicDeal implements Deal {
    
    /** The property being sold in this deal */
    private final Property property;
    
    /** The buyer in this transaction */
    private final User buyer;
    
    /** The seller in this transaction */
    private final User seller;
    
    /** The broker managing this transaction */
    private final Broker broker;

    /**
     * Constructs a basic deal with the specified participants.
     * 
     * @param property The property being sold
     * @param buyer The buyer in the transaction
     * @param seller The seller in the transaction
     * @param broker The broker managing the transaction
     */
    public BasicDeal(Property property, User buyer, User seller, Broker broker) {
        this.property = property;
        this.buyer = buyer;
        this.seller = seller;
        this.broker = broker;
    }

    /**
     * Executes the basic deal by displaying transaction information
     * and updating the property status to sold.
     */
    @Override
    public void executeDeal() {
        System.out.println("Broker " + broker.getUserId() + " : I'm managing this deal");
        System.out.println("Seller " + seller.getUserId() + " : I'm offering the property at " + Arrays.toString(property.getAddress()) + " for " + property.getTotalPrice());
        System.out.println("Buyer " + buyer.getUserId() + " : I'm interested in buying the property");
        property.setStatus(true); // Mark property as sold
    }

    /**
     * Returns the total price of the basic deal.
     * This is simply the property's total price without any additional services.
     * 
     * @return The property's total price
     */
    @Override
    public double getTotalPrice() {
        return property.getTotalPrice();
    }
}
