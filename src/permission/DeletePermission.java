package src.permission;

/**
 * Interface defining delete permissions for users in the real estate system.
 * Users with delete permissions can remove properties from the system.
 * 
 * This interface is implemented by user types that are allowed to
 * delete properties, such as sellers.
 * 
 * Delete permissions include:
 * - Removing properties from the system
 * - Triggering notifications to observers when properties are deleted
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.factory.Seller
 * @see src.observer.PropertyDeletionObserver
 */
public interface DeletePermission {
    
    /**
     * Deletes a property from the system.
     * 
     * @param address The address coordinates of the property to delete
     * @return true if the property was successfully deleted, false otherwise
     */
    boolean deleteProperty(int[] address);
} 