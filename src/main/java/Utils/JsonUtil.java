package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Author itmei
 * @Date 2024/5/26 12:59
 * @description:
 * @Title: JsonUtil
 * @Package com.util
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parseJsonToEntity(BufferedReader reader, Class<T> clazz) throws IOException {
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }

        String jsonData = jsonBuffer.toString();
        return objectMapper.readValue(jsonData, clazz);
    }
    public static String toJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }
}
