package src.decorator;

import java.util.Arrays;
import src.factory.Broker;
import src.factory.User;
import src.util.Property;

public class BasicDeal implements Deal {
    private final Property property;
    private final User buyer;
    private final User seller;
    private final Broker broker;

    public BasicDeal(Property property, User buyer, User seller, Broker broker) {
        this.property = property;
        this.buyer = buyer;
        this.seller = seller;
        this.broker = broker;
    }

    @Override
    public void executeDeal() {
        System.out.println("src.factory.Broker " + broker.getUserId() + " : I'm managing this deal");
        System.out.println("src.factory.Seller " + seller.getUserId() + " : I'm offering the property at " + Arrays.toString(property.getAddress()) + " for " + property.getTotalPrice());
        System.out.println("src.factory.Buyer " + buyer.getUserId() + " : I'm interested in buying the property");
        property.setStatus(true); // Mark property as sold
    }

    @Override
    public double getTotalPrice() {
        return property.getTotalPrice();
    }
}
