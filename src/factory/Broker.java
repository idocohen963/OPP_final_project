package src.factory;

import java.util.Arrays;
import src.main.SystemManager;
import src.observer.PropertyDeletionObserver;
import src.permission.EditPermission;
import src.permission.ViewPermission;
import src.util.Property;

/**
 * Represents a broker in the real estate system.
 * Brokers have view and edit permissions - they can view and modify property details
 * but cannot delete properties from the system.
 * 
 * This class implements the Observer pattern as an observer - brokers receive
 * notifications when sellers delete properties from the system.
 * 
 * Broker capabilities:
 * - View individual property details by address
 * - View all properties in the system
 * - Edit/update existing properties
 * - Receive notifications when properties are deleted
 * - Manage property deals between buyers and sellers
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.factory.User
 * @see src.permission.ViewPermission
 * @see src.permission.EditPermission
 * @see src.observer.PropertyDeletionObserver
 */
public class Broker extends User implements ViewPermission, EditPermission, PropertyDeletionObserver {
    
    /**
     * Constructs a new Broker with the specified user ID.
     * 
     * @param userId The unique identifier for this broker
     */
    public Broker(int userId) {
        super(userId, User.UserType.BROKER);
    }

    /**
     * Edits an existing property in the system.
     * 
     * @param address The current address of the property to edit
     * @param updatedProperty The new property data
     * @return true if the property was successfully updated, false otherwise
     */
    @Override
    public boolean editProperty(int[] address, Property updatedProperty) {
        return SystemManager.getInstance().updateProperty(address, updatedProperty);
    }

    /**
     * Receives notification when a property is deleted.
     * Part of the Observer pattern - this method is called by sellers
     * when they delete properties.
     * 
     * @param address The address of the deleted property
     */
    @Override
    public void onPropertyDeleted(int[] address) {
        System.out.println("Notification: Property at address " + Arrays.toString(address) + " has been deleted");
    }
} 