package org.myesai.spring.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class KafkaChannel {

    private final Producer producer;

    @Autowired
    public KafkaChannel(Producer producer) {
        this.producer = producer;
    }

    public <K,V> Future send(String topic, K key, V value){
        return producer.send(new ProducerRecord<K,V>(topic, key, value));
    }

}
