package com.mycompany;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Maven Practice with Java 17 ===");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        // 1. Работа с JSON (Gson)
        System.out.println("\n1. JSON Processing:");
        processJson();
        
        // 2. String utilities (Apache Commons)
        System.out.println("\n2. String Utilities:");
        processStrings();
        
        // 3. Новые фичи Java 17
        System.out.println("\n3. Java 17 Features:");
        java17Features();
    }
    
    private static void processJson() {
        Gson gson = new Gson();
        
        // Создаем объект
        Product product = new Product("Laptop", 999.99, 10);
        
        // В JSON
        String json = gson.toJson(product);
        System.out.println("Product JSON: " + json);
        
        // Обратно
        Product fromJson = gson.fromJson(json, Product.class);
        System.out.println("From JSON: " + fromJson);
    }
    
    private static void processStrings() {
        String text = "  Hello Maven World!  ";
        
        System.out.println("Original: '" + text + "'");
        System.out.println("Trimmed: '" + StringUtils.trim(text) + "'");
        System.out.println("Reversed: '" + StringUtils.reverse(text) + "'");
        System.out.println("Contains 'Maven': " + StringUtils.containsIgnoreCase(text, "maven"));
        System.out.println("Abbreviated: '" + StringUtils.abbreviate(text, 15) + "'");
    }
    
    private static void java17Features() {
        // Text Blocks (Java 15+)
        String jsonBlock = """
            {
                "feature": "Text Blocks",
                "version": "Java 15+",
                "multiline": true,
                "useful": true
            }
            """;
        System.out.println("Text Block Example:");
        System.out.println(jsonBlock);
        
        // Switch Expressions (Java 14+)
        Day day = Day.WEDNESDAY;
        String dayType = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working day";
            case SATURDAY, SUNDAY -> "Weekend";
        };
        System.out.println(day + " is a " + dayType);
        
        // Pattern Matching for instanceof (Java 16+)
        Object obj = "Pattern Matching Example";
        if (obj instanceof String s && s.length() > 10) {
            System.out.println("String length: " + s.length());
        }
        
        // Records (Java 16+)
        record Point(int x, int y) {
            public double distanceToOrigin() {
                return Math.sqrt(x * x + y * y);
            }
        }
        
        Point p = new Point(3, 4);
        System.out.println("Point: " + p);
        System.out.println("Distance to origin: " + p.distanceToOrigin());
    }
    
    // Record (Java 16+)
    record Product(String name, double price, int quantity) {
        public double totalValue() {
            return price * quantity;
        }
        
        @Override
        public String toString() {
            return String.format("Product[name='%s', price=%.2f, quantity=%d, total=%.2f]", 
                name, price, quantity, totalValue());
        }
    }
    
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}