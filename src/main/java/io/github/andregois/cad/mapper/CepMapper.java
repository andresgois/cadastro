package io.github.andregois.cad.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CepMapper {

	private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T mapFromJson(String json, Class<T> classe) throws Exception {
        return objectMapper.readValue(json, classe);
    }
}
