package src.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for reading property data from files.
 * This class handles the parsing and validation of property information
 * from text files with a specific format.
 * 
 * Expected file format for each property line:
 * address_coordinates area price_per_sqm is_sold
 * 
 * Example:
 * 4,5,1,1 80 10000 true
 * 
 * Where:
 * - address_coordinates: Comma-separated coordinates (street, avenue, subdivisions...)
 * - area: Property area in square meters
 * - price_per_sqm: Price per square meter in dollars
 * - is_sold: Boolean indicating if the property is sold
 * 
 * The class performs validation to ensure:
 * - All required fields are present
 * - Numeric values are valid
 * - No duplicate addresses exist
 * - Address coordinates are properly formatted
 * 
 * @see src.util.Property
 * @see src.main.SystemManager
 */
public class FileReaderProperty {
    
    /** List to store properties read from the file */
    private final List<Property> properties;

    /**
     * Constructs a new FileReaderProperty instance.
     * Initializes the internal properties list.
     */
    public FileReaderProperty() {
        this.properties = new ArrayList<>();
    }

    /**
     * Reads and parses properties from a file.
     * 
     * @param filePath The path to the file containing property data
     * @return A list of Property objects parsed from the file
     * @throws IllegalArgumentException if filePath is null
     * @throws RuntimeException if file reading fails or data is invalid
     */

    public List<Property> readPropertiesFromFile(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Property property = parsePropertyLine(line);
                validateProperty(property);
                properties.add(property);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + e.getMessage());
        }

        validateNoDuplicateAddresses();
        return new ArrayList<>(properties);
    }

    /**
     * Parses a single line from the property file into a Property object.
     * 
     * @param line The line to parse (format: "address area price_per_sqm is_sold")
     * @return A Property object created from the parsed data
     * @throws IOException if the line format is invalid or parsing fails
     */
    private Property parsePropertyLine(String line) throws IOException {
        String[] parts = line.trim().split(" "); 
        if (parts.length != 4) {
            throw new IOException("Invalid property format: expected 4 parts but found " + parts.length);
        }

        try {
            // Parse coordinates from first part (e.g., "4,5,1,1")
            String[] StrAddress = parts[0].split(",");
            if (StrAddress.length < 2) {
                throw new IOException("Address must contain at least street and avenue coordinates");
            }
            int[] IntAddress = new int[StrAddress.length];
            for (int i = 0; i < StrAddress.length; i++) {
                IntAddress[i] = Integer.parseInt(StrAddress[i]);
            }

            // Parse remaining parts
            double price = Double.parseDouble(parts[1]);
            double area = Double.parseDouble(parts[2]);
            boolean forRent = Boolean.parseBoolean(parts[3]);

            return new Property(IntAddress, price, area, forRent);

        } catch (NumberFormatException e) {
            throw new IOException("Error parsing numeric values: " + e.getMessage());
        }
    }

    /**
     * Validates a property object to ensure data integrity.
     * 
     * @param property The property to validate
     * @throws IllegalArgumentException if the property is invalid
     */
    private void validateProperty(Property property) {
        if (property == null) {
            throw new IllegalArgumentException("Property cannot be null");
        }
        if (property.getAddress() == null || property.getAddress().length < 2) {
            throw new IllegalArgumentException("Invalid property address");
        }
        // Additional property validations can be added here
    }

    /**
     * Validates that no duplicate addresses exist in the properties list.
     * Removes duplicates and throws an exception if any are found.
     * 
     * @throws IllegalArgumentException if duplicate addresses are found
     */
    private void validateNoDuplicateAddresses() {
        for (int i = 0; i < properties.size(); i++) {
            int[] currentAddress = properties.get(i).getAddress();
            
            for (int j = i + 1; j < properties.size(); j++) {
                int[] otherAddress = properties.get(j).getAddress();
                
                if (Arrays.equals(currentAddress, otherAddress)) {
                    properties.remove(j);
                    // Throw exception indicating duplicate was found and removed
                    throw new IllegalArgumentException("Duplicate address found and removed: " + Arrays.toString(currentAddress));
                }
            }
        }
    }
} 
