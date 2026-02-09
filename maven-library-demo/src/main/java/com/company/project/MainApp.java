package com.company.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Главное приложение, демонстрирующее использование библиотек
 */
public class MainApp {
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    public static void main(String[] args) {
        logger.info("=== Запуск Maven Library Demo ===");
        logger.info("Проект использует все библиотеки, которые попросил тимлид");
        
        try {
            DataProcessor processor = new DataProcessor();
            
            // 1. Создаем пример JSON
            logger.info("1. Создаем пример JSON...");
            String sampleJson = processor.createSampleJson();
            System.out.println("Созданный JSON:");
            System.out.println(sampleJson);
            
            // 2. Сохраняем в файл
            logger.info("2. Сохраняем JSON в файл...");
            File inputFile = new File("data/input.json");
            File outputFile = new File("data/output.txt");
            
            // Создаем директорию если нужно
            inputFile.getParentFile().mkdirs();
            
            // Используем Apache Commons IO
            org.apache.commons.io.FileUtils.writeStringToFile(
                inputFile, 
                sampleJson, 
                StandardCharsets.UTF_8
            );
            
            // 3. Обрабатываем файл
            logger.info("3. Обрабатываем файл...");
            processor.processJsonFile(
                inputFile.getPath(), 
                outputFile.getPath()
            );
            
            // 4. Показываем результат
            logger.info("4. Читаем результат...");
            String result = org.apache.commons.io.FileUtils.readFileToString(
                outputFile, 
                StandardCharsets.UTF_8
            );
            
            System.out.println("\nРезультат обработки:");
            System.out.println(result);
            
            logger.info("=== Программа завершена успешно ===");
            
        } catch (Exception e) {
            logger.error("Ошибка при выполнении программы", e);
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
