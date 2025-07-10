package src.decorator;

/**
 * Decorator that adds evening services to a property deal.
 * Part of the Decorator pattern implementation that allows adding
 * additional services to property transactions.
 * 
 * This decorator adds evening services with a fixed cost of $1000.
 * The evening services typically include:
 * - After-hours contract signing
 * - Evening property viewing arrangements
 * - Extended availability for deal completion
 * - Out-of-hours support and consultation
 * 
 * @see src.decorator.DealDecorator
 * @see src.decorator.Deal
 */
public class EveningServicesDecorator extends DealDecorator {
    
    /** The cost of evening services in dollars */
    private static final double EVENING_SERVICES_COST = 1000;

    /**
     * Constructs an evening services decorator for the specified deal.
     * 
     * @param deal The deal to be decorated with evening services
     */
    public EveningServicesDecorator(Deal deal) {
        super(deal);
    }

    /**
     * Executes the deal and adds evening services.
     * Calls the wrapped deal's execution and then adds the evening service.
     */
    @Override
    public void executeDeal() {
        deal.executeDeal();
        System.out.println("Adding evening services: " + EVENING_SERVICES_COST);
    }

    /**
     * Calculates the total price including evening services.
     * 
     * @return The base deal price plus the evening services cost
     */
    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice() + EVENING_SERVICES_COST;
    }
} 
