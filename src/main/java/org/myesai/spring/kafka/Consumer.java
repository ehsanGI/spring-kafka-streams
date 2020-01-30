package org.myesai.spring.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

public class Consumer<K,V> extends KafkaConsumer<K,V> {

    @Autowired
    public Consumer(KafkaConfig kafkaConfig) {
        super(kafkaConfig.getKafkaPros());
    }


}
