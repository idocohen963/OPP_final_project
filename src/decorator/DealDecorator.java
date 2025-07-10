package src.decorator;

/**
 * Abstract base class for all deal decorators in the real estate system.
 * Implements the Decorator pattern to allow adding additional services
 * to property deals.
 * 
 * This class provides the common structure for all decorators:
 * - Maintains a reference to the decorated deal
 * - Provides default implementations that delegate to the wrapped deal
 * - Allows subclasses to override specific methods to add functionality
 * 
 * Concrete decorators extend this class to add specific services like:
 * - Evening services
 * - Cleaning services
 * - Moving services
 * - Design services
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.decorator.Deal
 * @see src.decorator.BasicDeal
 * @see src.decorator.CleaningDecorator
 * @see src.decorator.MovingDecorator
 * @see src.decorator.DesignDecorator
 * @see src.decorator.EveningServicesDecorator
 */
public abstract class DealDecorator implements Deal {
    
    /** The deal being decorated */
    protected Deal deal;

    /**
     * Constructs a decorator for the specified deal.
     * 
     * @param deal The deal to be decorated with additional services
     */
    public DealDecorator(Deal deal) {
        this.deal = deal;
    }

    /**
     * Executes the deal by delegating to the wrapped deal.
     * Subclasses should override this method to add their specific functionality.
     */
    @Override
    public void executeDeal() {
        deal.executeDeal();
    }

    /**
     * Returns the total price by delegating to the wrapped deal.
     * Subclasses should override this method to add their service costs.
     * 
     * @return The total price from the wrapped deal
     */
    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice();
    }
} 