package org.haya.core.anno.kafka.clazz;

import java.util.List;

public interface KafkaSinkConfig {
    List<KafkaSinkTopicConfig> getTopicList();
}
