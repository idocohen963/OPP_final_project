package src.util;

import java.util.Arrays;

/**
 * Represents a property in the Manhattan real estate system.
 * Properties are apartments characterized by their location on a grid system,
 * area, price per square meter, and sale status.
 * 
 * The address system uses Manhattan's grid structure where:
 * - address[0] = street number
 * - address[1] = avenue number
 * - address[2..n] = apartment subdivisions (for subdivided apartments)
 * 
 * Examples:
 * - [4,5] represents a property at street 4, avenue 5
 * - [4,5,1] represents the first apartment in a subdivided property at street 4, avenue 5
 * - [4,5,1,1] represents further subdivision within the first apartment
 * 
 * @see src.util.FileReaderProperty
 * @see src.main.SystemManager
 */
public class Property {
    /** The address coordinates on Manhattan's grid system */
    protected final int[] address;
    
    /** The area of the property in square meters */
    protected double area;
    
    /** The price per square meter in dollars */
    protected double pricePerSquareMeter;
    
    /** The sale status - true if sold, false if available */
    protected boolean isSold;

    /**
     * Constructs a new Property with the specified parameters.
     * 
     * @param address The address coordinates array. Must contain at least 2 elements 
     *               (street and avenue). Additional elements represent apartment subdivisions.
     * @param area The area of the property in square meters. Must be positive.
     * @param pricePerSquareMeter The price per square meter in dollars. Must be positive.
     * @param isSold The initial sale status of the property
     * @throws IllegalArgumentException if address is null, has less than 2 elements,
     *                                  or if area/pricePerSquareMeter are not positive
     */

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

    /**
     * Calculates the total price of the property.
     * 
     * @return The total price calculated as area * pricePerSquareMeter
     */
    public double getTotalPrice() {
        return area * pricePerSquareMeter;
    }

    /**
     * Updates the sale status of the property.
     * 
     * @param sold The new sale status - true if sold, false if available
     */
    public void setStatus(boolean sold) {
        this.isSold = sold;
    }

    /**
     * Returns a copy of the property's address to prevent external modification.
     * 
     * @return A copy of the address array
     */
    public int[] getAddress() {
        return Arrays.copyOf(address, address.length);
    }

    /**
     * Gets the area of the property.
     * 
     * @return The area in square meters
     */
    public double getArea() {
        return area;
    }

    /**
     * Gets the price per square meter.
     * 
     * @return The price per square meter in dollars
     */
    public double getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    /**
     * Gets the sale status of the property.
     * 
     * @return true if the property is sold, false if available
     */
    public boolean getStatus() {
        return isSold;
    }

    /**
     * Returns a string representation of the property including all key information.
     * 
     * @return A formatted string containing address, area, price per square meter, and status
     */

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
