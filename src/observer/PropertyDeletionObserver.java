package src.observer;

/**
 * Observer interface for receiving notifications about property deletions.
 * Part of the Observer pattern implementation in the real estate system.
 * 
 * This interface defines the contract for objects that want to be notified
 * when properties are deleted from the system. Typically implemented by
 * brokers who need to be informed when sellers remove properties.
 * 
 * Observer Pattern participants:
 * - Subject: Sellers (who delete properties)
 * - Observer: Brokers (who get notified)
 * - ConcreteObserver: Broker class implements this interface
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.factory.Seller
 * @see src.factory.Broker
 */
public interface PropertyDeletionObserver {
    
    /**
     * Called when a property is deleted from the system.
     * Observers should implement this method to handle the notification.
     * 
     * @param address The address coordinates of the deleted property
     */
    void onPropertyDeleted(int[] address);
} 