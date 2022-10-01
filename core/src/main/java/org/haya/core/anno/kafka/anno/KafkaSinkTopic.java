package org.haya.core.anno.kafka.anno;

import org.apache.kafka.common.serialization.StringSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface KafkaSinkTopic {
    String name();

    String topicName();

    String brokerName();

    Class<?> serializer = StringSerializer.class;

    Class<?> deserializer = StringSerializer.class;

}
