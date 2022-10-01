package org.haya.core.aspect;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.haya.core.anno.kafka.clazz.KafkaSink;

public aspect KafkaSinkClazzAspect {

    pointcut all():execution(* *(..));

    pointcut kafkaSourcePoint(KafkaSink kafkaSinkAnnotation): @annotation(kafkaSinkAnnotation);

    pointcut processMethod(): execution(public * org.haya.core.task.AbstractTask.process(..));



    after(KafkaSink annotation) returning(DataStream<String> stream): all() && kafkaSourcePoint(annotation) && processMethod(){
        // todo
        stream.print();
    }
}
