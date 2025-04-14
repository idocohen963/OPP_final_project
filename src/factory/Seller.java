package src.factory;

import src.permission.DeletePermission;
import src.main.SystemManager;
import src.observer.PropertyDeletionObserver;
import src.permission.ViewPermission;

public class Seller extends User implements ViewPermission, DeletePermission {
    //this is the seller class that is used to create a seller
    private PropertyDeletionObserver observer;
    //constructor for the seller class
    public Seller(int userId) {
        super(userId, UserType.SELLER);
    }
    
    public void setObserver(PropertyDeletionObserver observer) {
        this.observer = observer;
    }
    
    @Override
    public boolean deleteProperty(int[] address) {
        boolean deleted = SystemManager.getInstance().removeProperty(address);
        if (deleted && observer != null) {
            observer.onPropertyDeleted(address);
        }
        return deleted;
    }
} 