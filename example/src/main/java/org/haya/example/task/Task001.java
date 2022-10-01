package org.haya.example.task;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.haya.core.anno.kafka.anno.KafkaSink;
import org.haya.core.anno.kafka.anno.KafkaSinkTopic;
import org.haya.core.anno.kafka.anno.KafkaSource;
import org.haya.core.anno.kafka.anno.KafkaSourceTopic;
import org.haya.core.task.AbstractTask;
import org.haya.example.enums.Topic;

public class Task001 extends AbstractTask {
    @KafkaSource(topics = {
            @KafkaSourceTopic(
                    id = Topic.TOSEE_COLLECT_EVENT,
                    brokerName = "haya",
                    topicName = "tosee-collect-event"
            )
    })
    @KafkaSink(topics = {
            @KafkaSinkTopic(topicName = "a", brokerName = "", name = "1")
    })
    @Override
    public DataStream<String> process() {
        var kafkaSourceMap = getContext().getKafkaSourceMap();
        return kafkaSourceMap.get(Topic.TOSEE_COLLECT_EVENT);
    }
}
