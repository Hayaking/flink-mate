package org.haya.example.task;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.haya.core.anno.kafka.clazz.KafkaSink;
import org.haya.core.anno.kafka.clazz.KafkaSource;
import org.haya.core.task.AbstractTask;
import org.haya.example.config.Task002KafkaSink;
import org.haya.example.config.Task002KafkaSource;

public class Task002 extends AbstractTask {

    @KafkaSource(configClass = Task002KafkaSource.class)
    @KafkaSink(configClass = Task002KafkaSink.class)
    @Override
    public DataStream<String> process() {
//        var kafkaSourceMap = getContext().getKafkaSourceMap();
//        return kafkaSourceMap.get(Topic.TOSEE_COLLECT_EVENT);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(getContext().getStreamEnv());
        return getContext().getStreamEnv().fromElements(
                "1", "2"
        ).map(item -> {
            System.out.println(item);
            return item;
        });
    }
}
