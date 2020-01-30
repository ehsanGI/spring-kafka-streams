package org.myesai.spring.kafka;

import com.hashbazar.server.kafka.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.streams.KeyValue;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class KafkaChannel {

    private final Producer producer;

    public <K,V> Future send(String topic, K key, V value, List<KeyValue<String, byte[]>> keyValues){
        ProducerRecord producerRecord = new ProducerRecord<>(topic, key, value);
        Headers recordHeaders = producerRecord.headers();
        keyValues
                .stream()
                .map(keyValue -> new RecordHeader(keyValue.key, keyValue.value))
                .forEach(kafkaHeadersEntry -> recordHeaders.add(kafkaHeadersEntry.key(), kafkaHeadersEntry.value()));

        return producer.send(producerRecord);
    }

    public Future<Object> send(ProducerRecord record) {
        return producer.send(record);
    }

//    package org.myesai.spring.kafka;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//import java.util.concurrent.Future;
//
//@Component
//@RequiredArgsConstructor
//public class KafkaChannel {
//
//    private final Producer producer;
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    public <K,V> Future send(String topic, K key, V value){
//        Message<V> message = MessageBuilder
//                .withPayload(value)
//                .setHeader(KafkaHeaders.TOPIC, topic)
//                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
//                .setHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID())
//                .build();
//
//        return kafkaTemplate.send(message);
//    }
//
//}


}
