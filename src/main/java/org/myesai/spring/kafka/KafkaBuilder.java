package org.myesai.spring.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

public class KafkaBuilder {

    public Topology prepareTopology(String input, String output, StreamProcessor streamProcessor) {
        StreamsBuilder builder = new StreamsBuilder();

        if (streamProcessor instanceof PipeProcessor) {
            PipeProcessor pipeProcessor = (PipeProcessor) streamProcessor;
            pipeProcessor.process(bindInput(builder, input))
                    .to(output, Produced.with(Serdes.String(), Serdes.String()));
        } else {
            SinkProcessor pipeProcessor = (SinkProcessor) streamProcessor;
            pipeProcessor.process(bindInput(builder, input));
        }

        return builder.build();
    }

    private KStream<String, String> bindInput(StreamsBuilder streamsBuilder, String input) {
        return streamsBuilder.stream(input);
    }
}
