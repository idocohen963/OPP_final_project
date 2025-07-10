package src.decorator;

/**
 * Decorator that adds cleaning services to a property deal.
 * Part of the Decorator pattern implementation that allows adding
 * additional services to property transactions.
 * 
 * This decorator adds cleaning services with a fixed cost of $2000.
 * The cleaning services typically include:
 * - Professional cleaning before buyer move-in
 * - Deep cleaning of all areas
 * - Preparation of property for new occupancy
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.decorator.DealDecorator
 * @see src.decorator.Deal
 */
public class CleaningDecorator extends DealDecorator {
    
    /** The cost of cleaning services in dollars */
    private static final double CLEANING_SERVICES_COST = 2000;

    /**
     * Constructs a cleaning decorator for the specified deal.
     * 
     * @param deal The deal to be decorated with cleaning services
     */
    public CleaningDecorator(Deal deal) {
        super(deal);
    }

    /**
     * Executes the deal and adds cleaning services.
     * Calls the wrapped deal's execution and then adds the cleaning service.
     */
    @Override
    public void executeDeal() {
        deal.executeDeal();
        System.out.println("Adding cleaning services: " + CLEANING_SERVICES_COST);
    }

    /**
     * Calculates the total price including cleaning services.
     * 
     * @return The base deal price plus the cleaning services cost
     */
    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice() + CLEANING_SERVICES_COST;
    }
} 