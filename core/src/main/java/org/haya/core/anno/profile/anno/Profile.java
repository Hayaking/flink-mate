package org.haya.core.anno.profile.anno;

import org.haya.core.anno.kafka.anno.KafkaBroker;
import org.haya.core.anno.redis.Redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Profile {
    String env();


    KafkaBroker[] kafkaConfigs() default {};

    Redis[] redisConfigs() default {};
}
