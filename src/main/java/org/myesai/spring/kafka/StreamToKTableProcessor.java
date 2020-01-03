package org.myesai.spring.kafka;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

public interface StreamToKTableProcessor extends StreamProcessor {
    KTable<String, String> process(KStream<String, String> inputStream);
}
