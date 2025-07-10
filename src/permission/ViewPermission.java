package src.permission;

import src.main.SystemManager;
import src.util.Property;

import java.util.Arrays;
import java.util.List;

/**
 * Interface defining view permissions for users in the real estate system.
 * Provides default implementations for viewing property information.
 * 
 * This interface uses default methods to provide common functionality
 * that can be used by all user types that have view permissions.
 * 
 * View permissions include:
 * - Viewing individual property details by address
 * - Viewing all properties in the system
 * 
 * @see src.factory.Buyer
 * @see src.factory.Seller
 * @see src.factory.Broker
 */
public interface ViewPermission {
    
    /**
     * Views a specific property by its address.
     * 
     * @param address The address coordinates of the property to view
     * @return The property at the specified address, or null if not found
     * @throws IllegalArgumentException if address is null
     */
    default Property viewProperty(int[] address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        
        List<Property> properties = SystemManager.getInstance().getAllProperties();
        for (Property property : properties) {
            if (Arrays.equals(property.getAddress(), address)) {
                System.out.println("Property found: " + property.toString());
                return property;
            }
        }
        return null;
    }

    /**
     * Views all properties in the system.
     * 
     * @return A list of all properties in the system
     */
    default List<Property> viewAllProperties() {
        return SystemManager.getInstance().getAllProperties();
    }
} 
