package org.haya.core.anno.kafka.anno;

import org.apache.kafka.common.serialization.StringSerializer;
import org.haya.core.filter.KafkaSourceFilter;
import org.haya.core.filter.NullKafkaSourceFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface KafkaSourceTopic {

    String id();

    String topicName();

    String brokerName();

    Class<?> serializer() default StringSerializer.class;

    Class<?> deserializer() default StringSerializer.class;

    Class<? extends KafkaSourceFilter> filter() default NullKafkaSourceFilter.class;

    String consumerGroup() default "";

    String offsetStrategy() default "latest";

}
