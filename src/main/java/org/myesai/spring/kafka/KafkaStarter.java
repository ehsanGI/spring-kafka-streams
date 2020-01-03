package org.myesai.spring.kafka;

import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.StateRestoreListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

        KafkaStreams kafkaStreams = new KafkaStreams(topology, kafkaConfig.getKafkaPros());
        kafkaStreams.setGlobalStateRestoreListener(new StateRestoreListener() {
            @Override
            public void onRestoreStart(TopicPartition topicPartition, String s, long l, long l1) {
                System.out.println("-------------------- onRestoreStart");
            }

            @Override
            public void onBatchRestored(TopicPartition topicPartition, String s, long l, long l1) {
                System.out.println("-------------------- onBatchRestored");
            }

            @Override
            public void onRestoreEnd(TopicPartition topicPartition, String s, long l) {
                System.out.println("-------------------- onRestoreEnd");
            }
        });
        kafkaStreams.start();
    }
}
