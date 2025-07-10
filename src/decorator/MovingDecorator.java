package src.decorator;

/**
 * Decorator that adds moving services to a property deal.
 * Part of the Decorator pattern implementation that allows adding
 * additional services to property transactions.
 * 
 * This decorator adds moving services with a fixed cost of $3000.
 * The moving services typically include:
 * - Professional moving company coordination
 * - Packing and unpacking services
 * - Transportation of belongings
 * - Moving insurance coverage
 * 
 * @see src.decorator.DealDecorator
 * @see src.decorator.Deal
 */
public class MovingDecorator extends DealDecorator {

    /** The cost of moving services in dollars */
    private static final double MOVING_SERVICES_COST = 3000;

    /**
     * Constructs a moving decorator for the specified deal.
     * 
     * @param deal The deal to be decorated with moving services
     */
    public MovingDecorator(Deal deal) {
        super(deal);
    }

    /**
     * Executes the deal and adds moving services.
     * Calls the wrapped deal's execution and then adds the moving service.
     */
    @Override
    public void executeDeal() {
        deal.executeDeal();
        System.out.println("Adding moving services: " + MOVING_SERVICES_COST);
    }

    /**
     * Calculates the total price including moving services.
     * 
     * @return The base deal price plus the moving services cost
     */
    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice() + MOVING_SERVICES_COST;
    }
} 
