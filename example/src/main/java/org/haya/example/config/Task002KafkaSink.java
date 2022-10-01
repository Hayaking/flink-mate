package org.haya.example.config;

import org.haya.core.anno.kafka.clazz.KafkaSinkConfig;
import org.haya.core.anno.kafka.clazz.KafkaSinkTopicConfig;

import java.util.List;

public class Task002KafkaSink implements KafkaSinkConfig {
    @Override
    public List<KafkaSinkTopicConfig> getTopicList() {
        return List.of(
                new KafkaSinkTopicConfig()
        );
    }
}
