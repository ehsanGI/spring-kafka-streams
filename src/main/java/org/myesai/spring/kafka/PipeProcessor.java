package org.myesai.spring.kafka;

import org.apache.kafka.streams.kstream.KStream;

public interface PipeProcessor extends StreamProcessor {
    KStream<String, String> process(KStream<String, String> inputStream);
}
