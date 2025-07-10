package src.decorator;

/**
 * Interface defining the contract for property deals in the real estate system.
 * Part of the Decorator pattern implementation that allows adding additional
 * services to property deals.
 * 
 * This interface defines the core operations that all deals must support:
 * - Executing the deal
 * - Calculating the total price
 * 
 * The Decorator pattern allows for flexible addition of services like:
 * - Evening services
 * - Cleaning services  
 * - Moving services
 * - Design services
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.decorator.BasicDeal
 * @see src.decorator.DealDecorator
 */
public interface Deal {
    
    /**
     * Executes the property deal.
     * This method handles the actual transaction process and updates
     * the property status accordingly.
     */
    void executeDeal();
    
    /**
     * Calculates the total price of the deal.
     * This includes the base property price plus any additional services.
     * 
     * @return The total price of the deal including all services
     */
    double getTotalPrice();
}

