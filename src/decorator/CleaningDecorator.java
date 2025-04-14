package src.decorator;

public class CleaningDecorator extends DealDecorator {
    private static final double CLEANING_SERVICES_COST = 2000;

    public CleaningDecorator(Deal deal) {
        super(deal);
    }

    @Override
    public void executeDeal() {
        deal.executeDeal();
        System.out.println("Adding cleaning services: " + CLEANING_SERVICES_COST);
    }

    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice() + CLEANING_SERVICES_COST;
    }
} 