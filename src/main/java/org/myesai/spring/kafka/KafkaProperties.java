package org.myesai.spring.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaProperties {

    final String applicationId;
    final String broker;
    final String inputTopic;
    final String outputTopic;

    public KafkaProperties(@Value("${kafka.applicationId}") String applicationId,
                           @Value("${kafka.broker}") String broker,
                           @Value("${kafka.inputTopic}") String inputTopic,
                           @Value("${kafka.outputTopic:DEFAULT}") String outputTopic) {

        this.applicationId = applicationId;
        this.broker = broker;
        this.inputTopic = inputTopic;
        this.outputTopic = outputTopic;
    }
}
