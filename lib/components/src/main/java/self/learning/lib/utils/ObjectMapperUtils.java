package self.learning.lib.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ObjectMapperUtils {

    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(AppDateUtils.DEFAULT_DATE_FORMAT));
    }

    private ObjectMapperUtils() {}

    public static <T> Optional<T> readValue(String content, Class<T> valueType)
            throws JsonMappingException, JsonProcessingException {
        if (content == null) {
            return Optional.empty();
        } else {
            T value = objectMapper.readValue(content, valueType);
            return Optional.of(value);
        }
    }

    public static String writeValueAsString(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }

	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertValueToMap(Object object) {
		if (object == null)
			return new HashMap<String, Object>();

		return (Map<String, Object>) objectMapper.convertValue(object, Map.class);
	}

    public static ObjectMapper getMapper() {
        return objectMapper;
    }
}
