package src.factory;

import src.permission.ViewPermission;

public class Buyer extends User implements ViewPermission {
    public Buyer(int userId) {
        super(userId, User.UserType.BUYER);
    }
    //we made the viewProperty and viewAllProperties methods
    // in the src.permission.ViewPermission interface default methods.
}   