package com.xux.generator.freemarker.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.json.JsonParseException;

import java.io.IOException;
@Slf4j
public class JsonHelper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String getJSONString(Object obj) {
        String result = "";
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            log.error("", e);
        } catch (JsonMappingException e) {
            log.error("", e);
        } catch (IOException e) {
            log.error("", e);
        }
        return result;
    }

    public static Object getObjectByJSON(String jsonStr) {
        Object obj = null;
        try {
            obj = mapper.readValue(jsonStr, Object.class);
        } catch (JsonParseException e) {
            log.error("", e);
        } catch (JsonMappingException e) {
            log.error("", e);
        } catch (IOException e) {
            log.error("", e);
        }
        return obj;
    }
}
