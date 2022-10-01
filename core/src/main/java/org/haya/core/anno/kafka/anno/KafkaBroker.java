package org.haya.core.anno.kafka.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Config
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface KafkaBroker {
    String name();

    String brokers();

    String offsetStrategy() default "latest";
}
