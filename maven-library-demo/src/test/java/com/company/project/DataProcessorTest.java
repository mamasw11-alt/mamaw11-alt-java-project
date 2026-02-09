package com.company.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Path;

class DataProcessorTest {
    
    @TempDir
    Path tempDir;
    
    @Test
    void testCreateSampleJson() throws Exception {
        DataProcessor processor = new DataProcessor();
        String json = processor.createSampleJson();
        
        assertNotNull(json);
        assertTrue(json.contains("Test Project"));
        assertTrue(json.contains("1.0.0"));
    }
    
    @Test
    void testJsonContainsValidStructure() throws Exception {
        DataProcessor processor = new DataProcessor();
        String json = processor.createSampleJson();
        
        // Проверяем что JSON содержит основные поля
        assertTrue(json.contains("\"name\""));
        assertTrue(json.contains("\"version\""));
        assertTrue(json.contains("\"dependencies\""));
    }
}
