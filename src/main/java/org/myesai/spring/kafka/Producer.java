package org.myesai.spring.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer<K,V> extends KafkaProducer<K,V> {

    @Autowired
    public Producer(KafkaConfig kafkaConfig) {
        super(kafkaConfig.getKafkaPros());
    }
}
