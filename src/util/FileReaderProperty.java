package src.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderProperty {
    private final List<Property> properties;

    public FileReaderProperty() {
        this.properties = new ArrayList<>();
    }

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

    private void validateProperty(Property property) {
        if (property == null) {
            throw new IllegalArgumentException("src.util.Property cannot be null");
        }
        if (property.getAddress() == null || property.getAddress().length < 2) {
            throw new IllegalArgumentException("Invalid property address");
        }
        // ... other property validations ...
    }

    private void validateNoDuplicateAddresses() {
        for (int i = 0; i < properties.size(); i++) {
            int[] currentAddress = properties.get(i).getAddress();
            
            for (int j = i + 1; j < properties.size(); j++) {
                int[] otherAddress = properties.get(j).getAddress();
                
                if (Arrays.equals(currentAddress, otherAddress)) {
                    properties.remove(j);
                    // No need to decrement j since properties.size() will be updated automatically
                    // after remove() and the loop condition will use the new size לעשות על זה טקסט שיציג את הכתובת שנמחקה  
                    throw new IllegalArgumentException("Duplicate address found and removed: " + Arrays.toString(currentAddress));
                }
            }
        }
    }
} 