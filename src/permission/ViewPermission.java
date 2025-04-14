package src.permission;

import src.main.SystemManager;
import src.util.Property;

import java.util.Arrays;
import java.util.List;

public interface ViewPermission {
    default Property viewProperty(int[] address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        
        List<Property> properties = SystemManager.getInstance().getAllProperties();
        for (Property property : properties) {
            if (Arrays.equals(property.getAddress(), address)) {
                System.out.println("src.util.Property found: " + property.toString());
                return property;
            }
        }
        return null;
    }

    default List<Property> viewAllProperties() {
        return SystemManager.getInstance().getAllProperties();
    }
} 