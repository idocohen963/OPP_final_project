package src.decorator;

/**
 * Decorator that adds design services to a property deal.
 * Part of the Decorator pattern implementation that allows adding
 * additional services to property transactions.
 * 
 * This decorator adds design services with a fixed cost of $4000.
 * The design services typically include:
 * - Interior design consultation
 * - Space planning and optimization
 * - Color scheme and decoration recommendations
 * - Furniture arrangement suggestions
 * 
 * @see src.decorator.DealDecorator
 * @see src.decorator.Deal
 */
public class DesignDecorator extends DealDecorator {
    
    /** The cost of design services in dollars */
    private static final double DESIGN_SERVICES_COST = 4000;

    /**
     * Constructs a design decorator for the specified deal.
     * 
     * @param deal The deal to be decorated with design services
     */
    public DesignDecorator(Deal deal) {
        super(deal);
    }

    /**
     * Executes the deal and adds design services.
     * Calls the wrapped deal's execution and then adds the design service.
     */
    @Override
    public void executeDeal() {
        deal.executeDeal();
        System.out.println("Adding design services: " + DESIGN_SERVICES_COST);
    }

    /**
     * Calculates the total price including design services.
     * 
     * @return The base deal price plus the design services cost
     */
    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice() + DESIGN_SERVICES_COST;
    }
} 
