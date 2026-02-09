package com.company.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Класс для обработки данных, использующий библиотеки,
 * которые попросил добавить тимлид
 */
public class DataProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DataProcessor.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Обрабатывает JSON файл и сохраняет результат
     */
    public void processJsonFile(String inputPath, String outputPath) throws IOException {
        logger.info("Начинаем обработку файла: {}", inputPath);
        
        // 1. Чтение файла (Apache Commons IO)
        String jsonContent = FileUtils.readFileToString(
            new File(inputPath), 
            StandardCharsets.UTF_8
        );
        
        logger.debug("Прочитано {} байт", jsonContent.length());
        
        // 2. Парсинг JSON (Jackson)
        Map<String, Object> data = objectMapper.readValue(jsonContent, Map.class);
        
        // 3. Обработка данных (Guava + Apache Commons Lang3)
        List<String> processedItems = processData(data);
        
        // 4. Объединение результатов (Guava)
        String result = Joiner.on("\n").join(processedItems);
        
        // 5. Сохранение результата (Apache Commons IO)
        FileUtils.writeStringToFile(
            new File(outputPath), 
            result, 
            StandardCharsets.UTF_8
        );
        
        logger.info("Обработка завершена. Результат сохранен в: {}", outputPath);
    }
    
    /**
     * Обрабатывает данные из JSON
     */
    private List<String> processData(Map<String, Object> data) {
        List<String> result = Lists.newArrayList();
        
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            // Используем Apache Commons Lang3 для обработки строк
            String processedKey = StringUtils.capitalize(key.toLowerCase());
            String processedValue = StringUtils.trimToEmpty(value.toString());
            
            // Форматируем результат
            String line = String.format("%s: %s", processedKey, processedValue);
            result.add(line);
            
            logger.debug("Обработана запись: {} -> {}", key, processedValue);
        }
        
        return result;
    }
    
    /**
     * Создает пример JSON для тестирования
     */
    public String createSampleJson() throws IOException {
        Map<String, Object> sampleData = Map.of(
            "name", "Test Project",
            "version", "1.0.0",
            "author", "John Doe",
            "description", "Пример проекта с Maven",
            "dependencies", List.of("Jackson", "Guava", "Apache Commons")
        );
        
        return objectMapper.writerWithDefaultPrettyPrinter()
                          .writeValueAsString(sampleData);
    }
}
