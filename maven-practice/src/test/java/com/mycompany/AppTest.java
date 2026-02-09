package com.mycompany;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

public class AppTest {
    
    @Test
    public void testProductRecord() {
        App.Product product = new App.Product("Test", 10.0, 5);
        
        assertEquals("Test", product.name());
        assertEquals(10.0, product.price());
        assertEquals(5, product.quantity());
        assertEquals(50.0, product.totalValue());
    }
    
    @Test
    public void testJsonConversion() {
        Gson gson = new Gson();
        App.Product product = new App.Product("Test", 20.0, 3);
        
        String json = gson.toJson(product);
        App.Product fromJson = gson.fromJson(json, App.Product.class);
        
        assertEquals(product.name(), fromJson.name());
        assertEquals(product.price(), fromJson.price());
        assertEquals(product.quantity(), fromJson.quantity());
    }
    
    @Test
    public void testStringUtils() {
        assertEquals("HELLO", StringUtils.upperCase("hello"));
        assertTrue(StringUtils.containsIgnoreCase("Hello World", "WORLD"));
        assertEquals("Hello...", StringUtils.abbreviate("Hello World", 8));
    }
}
