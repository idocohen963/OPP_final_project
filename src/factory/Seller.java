package src.factory;

import src.permission.DeletePermission;
import src.main.SystemManager;
import src.observer.PropertyDeletionObserver;
import src.permission.ViewPermission;

/**
 * Represents a seller in the real estate system.
 * Sellers have view and delete permissions - they can view property details
 * and delete properties from the system.
 * 
 * This class implements the Observer pattern as a subject - when a seller
 * deletes a property, all registered observers (brokers) are notified.
 * 
 * Seller capabilities:
 * - View individual property details by address
 * - View all properties in the system
 * - Delete properties from the system
 * - Notify brokers when properties are deleted
 * - Participate in property deals as the selling party
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.factory.User
 * @see src.permission.ViewPermission
 * @see src.permission.DeletePermission
 * @see src.observer.PropertyDeletionObserver
 */
public class Seller extends User implements ViewPermission, DeletePermission {
    
    /** Observer to be notified when properties are deleted */
    private PropertyDeletionObserver observer;
    
    /**
     * Constructs a new Seller with the specified user ID.
     * 
     * @param userId The unique identifier for this seller
     */
    public Seller(int userId) {
        super(userId, UserType.SELLER);
    }
    
    /**
     * Sets the observer to be notified when properties are deleted.
     * Part of the Observer pattern implementation.
     * 
     * @param observer The observer to notify (typically a broker)
     */
    public void setObserver(PropertyDeletionObserver observer) {
        this.observer = observer;
    }
    
    /**
     * Deletes a property from the system and notifies observers.
     * 
     * @param address The address of the property to delete
     * @return true if the property was successfully deleted, false otherwise
     */
    @Override
    public boolean deleteProperty(int[] address) {
        boolean deleted = SystemManager.getInstance().removeProperty(address);
        if (deleted && observer != null) {
            observer.onPropertyDeleted(address);
        }
        return deleted;
    }
} 