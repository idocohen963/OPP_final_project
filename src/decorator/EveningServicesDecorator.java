package src.decorator;

//this is the evening services decorator class that is used to add evening services to the deal
public class EveningServicesDecorator extends DealDecorator {
    private static final double EVENING_SERVICES_COST = 1000;

    public EveningServicesDecorator(Deal deal) {
        super(deal);
    }

    @Override
    public void executeDeal() {
        deal.executeDeal();
        System.out.println("Adding evening services: " + EVENING_SERVICES_COST);
    }

    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice() + EVENING_SERVICES_COST;
    }
} 