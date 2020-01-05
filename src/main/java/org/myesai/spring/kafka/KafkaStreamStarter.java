package org.myesai.spring.kafka;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;

public class KafkaStreamStarter {

    private final KafkaConfig kafkaConfig;
    private final StreamProcessor streamProcessor;
    private final String inputTopic;
    private String outputTopic;

    public KafkaStreamStarter(
            KafkaConfig kafkaConfig, StreamProcessor streamProcessor, String inputTopic) {
        this.kafkaConfig = kafkaConfig;
        this.streamProcessor = streamProcessor;
        this.inputTopic = inputTopic;
    }

    public void setOutputTopic(String outputTopic) {
        this.outputTopic = outputTopic;
    }

    public KafkaStreams startKafka() {
        Topology topology = new KafkaBuilder()
                .prepareTopology(inputTopic, outputTopic, streamProcessor);

        KafkaStreams kafkaStreams = new KafkaStreams(topology, kafkaConfig.getKafkaPros());
        kafkaStreams.start();

        return kafkaStreams;
    }
}
