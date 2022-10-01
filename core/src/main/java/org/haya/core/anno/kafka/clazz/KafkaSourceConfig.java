package org.haya.core.anno.kafka.clazz;

import java.util.List;

public interface KafkaSourceConfig {
    List<KafkaSourceTopicConfig> getTopicList();
}
