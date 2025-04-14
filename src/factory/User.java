package src.factory;

public abstract class User {
    //this is the user class that is used to create a user
    //3 types of users: buyer, seller, broker
    public enum UserType {
        BUYER,
        SELLER,
        BROKER
    }

    protected final int userId;
    protected final UserType userType;
    //constructor for the user class
    protected User(int userId, UserType userType) {
        if (userId < 0) {
            throw new IllegalArgumentException("src.factory.User ID cannot be negative");
        }
        this.userId = userId;
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }
    
} 