package org.myesai.spring.kafka;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaStarter {

    private final KafkaConfig kafkaConfig;
    private StreamProcessor streamProcessor;

    @Autowired
    public KafkaStarter(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @Autowired(required = false)
    public void setStreamProcessor(StreamProcessor streamProcessor) {
        this.streamProcessor = streamProcessor;
    }
    public void startKafka() {
        Topology topology = new KafkaBuilder()
                .prepareTopology(kafkaConfig.inputTopic(), kafkaConfig.outputTopic(), streamProcessor);

        new KafkaStreams(topology, kafkaConfig.getKafkaPros()).start();
    }
}
