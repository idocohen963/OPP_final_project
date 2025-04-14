package src.factory;

import java.util.Arrays;
import src.main.SystemManager;
import src.observer.PropertyDeletionObserver;
import src.permission.EditPermission;
import src.permission.ViewPermission;
import src.util.Property;

public class Broker extends User implements ViewPermission, EditPermission, PropertyDeletionObserver {
    public Broker(int userId) {
        super(userId, User.UserType.BROKER);
    }

    @Override
    public boolean editProperty(int[] address, Property updatedProperty) {
        return SystemManager.getInstance().updateProperty(address, updatedProperty);
    }

    @Override
    public void onPropertyDeleted(int[] address) {
        System.out.println("Notification: src.util.Property at address " + Arrays.toString(address) + " has been deleted");
    }
} 