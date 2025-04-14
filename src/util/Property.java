package src.util;

import java.util.Arrays;

public class Property {
    protected final int[] address;
    protected double area;
    protected double pricePerSquareMeter;
    protected boolean isSold;

    public Property(int[] address, double area, double pricePerSquareMeter, boolean isSold) {
        if (address == null || address.length < 2) {
            throw new IllegalArgumentException("Address must contain at least street and avenue coordinates");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Area must be positive");
        }
        if (pricePerSquareMeter <= 0) {
            throw new IllegalArgumentException("Price per square meter must be positive");
        }

        this.address = Arrays.copyOf(address, address.length);
        this.area = area;
        this.pricePerSquareMeter = pricePerSquareMeter;
        this.isSold = isSold;
    }

    public double getTotalPrice() {
        return area * pricePerSquareMeter;
    }

    public void setStatus(boolean sold) {
        this.isSold = sold;
    }

    public int[] getAddress() {
        return Arrays.copyOf(address, address.length);
    }

    public double getArea() {
        return area;
    }

    public double getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    public boolean getStatus() {
        return isSold;
    }

        @Override
    public String toString() {
        return "address: "+Arrays.toString(address)+" area: "+area+" pricePerSquareMeter: "+pricePerSquareMeter+" status: "+isSold;
    }

/*
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof src.util.Property)) return false;
        src.util.Property property = (src.util.Property) o;
        return Arrays.equals(address, property.address) &&
               area == property.area &&
               pricePerSquareMeter == property.pricePerSquareMeter &&
               isSold == property.isSold;
    }    

    @Override
    public int hashCode() {
        return Arrays.hashCode(address);
    }       */

}
