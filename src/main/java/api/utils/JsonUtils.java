package api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.List;

public class JsonUtils {
    public static <T> List<T> convertToList(Response response, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(response.as(List.class),
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
