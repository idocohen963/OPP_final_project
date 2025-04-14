package src.test.java;

import org.junit.Before;
import org.junit.Test;
import src.util.FileReaderProperty;
import src.util.Property;

import java.util.List;

import static org.junit.Assert.*;
//good
public class TestFileReaderProperty {
    private FileReaderProperty fileReader;

    // Initialize the src.util.FileReaderProperty instance before each test
    @Before
    public void setUp() {
        fileReader = new FileReaderProperty();
    }

    // Test reading an empty file
    @Test
    public void testReadEmptyFile() {
        List<Property> properties = fileReader.readPropertiesFromFile("src/test/java/Tempty.txt");
        assertNotNull("The list should not be null", properties);
        assertTrue("The list should be empty", properties.isEmpty());
    }

    // Test reading a file with duplicate addresses
    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateAddresses() {
        // This file contains two properties with the same address (4,5)
        fileReader.readPropertiesFromFile("src/test/java/TduplicateAddresses");
    }

    // Test reading a file with invalid properties
    // We expect a RuntimeException because src.util.FileReaderProperty throws an exception
    // when it encounters invalid lines in the file
    @Test(expected = RuntimeException.class)
    public void testReadInvalidProperties() {
        fileReader.readPropertiesFromFile("src/test/java/TinvalidProperties.txt");
    }

    // Test handling of null file path
    @Test(expected = IllegalArgumentException.class)
    public void testNullFilePath() {
        fileReader.readPropertiesFromFile(null);
    }

    // Test handling of non-existent file
    @Test
    public void testNonExistentFile() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            fileReader.readPropertiesFromFile("nonexistent.txt");
        });
        assertTrue("The message should indicate a reading error",
                exception.getMessage().contains("Error reading from file"));
    }
}