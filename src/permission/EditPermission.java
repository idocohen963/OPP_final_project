package src.permission;

import src.util.Property;

/**
 * Interface defining edit permissions for users in the real estate system.
 * Users with edit permissions can modify existing property information.
 * 
 * This interface is implemented by user types that are allowed to
 * edit property details, such as brokers.
 * 
 * Edit permissions include:
 * - Updating existing property information
 * - Modifying property details while maintaining data integrity
 * 
 * @author Manhattan Real Estate System
 * @since 1.0
 * @see src.factory.Broker
 * @see src.util.Property
 */
public interface EditPermission {
    
    /**
     * Edits an existing property in the system.
     * 
     * @param address The current address of the property to edit
     * @param updatedProperty The new property data to replace the existing property
     * @return true if the property was successfully updated, false otherwise
     */
    boolean editProperty(int[] address, Property updatedProperty);
} 