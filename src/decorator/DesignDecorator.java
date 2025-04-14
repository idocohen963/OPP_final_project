package src.decorator;

//this is the design decorator class that is used to add design services to the deal
public class DesignDecorator extends DealDecorator {
    private static final double DESIGN_SERVICES_COST = 4000;

    public DesignDecorator(Deal deal) {
        super(deal);
    }

    @Override
    public void executeDeal() {
        deal.executeDeal();
        System.out.println("Adding design services: " + DESIGN_SERVICES_COST);
    }

    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice() + DESIGN_SERVICES_COST;
    }
} 