package org.haya.example.config;

import org.haya.core.anno.kafka.clazz.KafkaSourceConfig;
import org.haya.core.anno.kafka.clazz.KafkaSourceTopicConfig;

import java.util.List;

public class Task002KafkaSource implements KafkaSourceConfig {
    @Override
    public List<KafkaSourceTopicConfig> getTopicList() {
        return List.of(
                new KafkaSourceTopicConfig(),
                new KafkaSourceTopicConfig()
        );
    }
}
