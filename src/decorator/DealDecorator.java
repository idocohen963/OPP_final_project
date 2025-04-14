package src.decorator;

public abstract class DealDecorator implements Deal {
    protected Deal deal;

    public DealDecorator(Deal deal) {
        this.deal = deal;
    }

    @Override
    public void executeDeal() {
        deal.executeDeal();
    }

    @Override
    public double getTotalPrice() {
        return deal.getTotalPrice();
    }
} 