package org.myesai.spring.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.springframework.context.annotation.ComponentScan;

public class KafkaJsonUtil {

    ObjectMapper objectMapper = new ObjectMapper();

    public String getStringFromObject(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
