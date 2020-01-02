package org.myesai.spring.kafka;

import org.apache.kafka.streams.kstream.KStream;

public interface SinkProcessor extends StreamProcessor {
    void process(KStream<String, String> inputStream);
}
