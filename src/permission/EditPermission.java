package src.permission;

import src.util.Property;

public interface EditPermission {
    boolean editProperty(int[] address, Property updatedProperty);
} 