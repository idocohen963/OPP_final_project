package src.decorator;

//this is the moving decorator class that is used to add moving services to the deal
public class MovingDecorator extends DealDecorator {

    private static final double MOVING_SERVICES_COST = 3000;

    public MovingDecorator(Deal deal) {
        super(deal);
    }

    @Override
    public void executeDeal() {
        deal.executeDeal();
        System.out.println("Adding moving services: " + MOVING_SERVICES_COST);
    }

    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice() + MOVING_SERVICES_COST;
    }
} 