package org.haya.core.aspect;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.haya.core.Context;
import org.haya.core.anno.kafka.anno.KafkaSource;

import java.util.Properties;

public aspect KafkaSourceAnnoAspect {

    pointcut all():execution(* *(..));

    pointcut kafkaSourcePoint(KafkaSource kafkaSourceAnnotation): @annotation(kafkaSourceAnnotation);

    pointcut processMethod(): execution(public * org.haya.core.task.AbstractTask.process(..));


    before(KafkaSource annotation): all() && kafkaSourcePoint(annotation) && processMethod(){
//        var task = ((AbstractTask) thisJoinPoint.getTarget());
        var context = Context.getInstance();
        var env = context.getStreamEnv();
        var configMap = env
                .getConfig()
                .getGlobalJobParameters()
                .toMap();


        Properties props = new Properties();
//        props.setProperty(FETCH_MAX_BYTES_CONFIG, config.getFetchMaxBytes());
//        props.setProperty(MAX_POLL_RECORDS_CONFIG, config.getMaxPollRecords());
//        props.setProperty(MAX_POLL_INTERVAL_MS_CONFIG, config.getMaxPollIntervalMs());
//        props.setProperty(FETCH_MAX_WAIT_MS_CONFIG, config.getFetchMaxWaitMs());
//        props.setProperty(REQUEST_TIMEOUT_MS_CONFIG, config.getRequestTimeoutMs());
//        props.setProperty(ENABLE_AUTO_COMMIT_CONFIG, true);
//        var offsetStrategy = config.getOffsetStrategy();

        var kafkaSourceMap = context.getKafkaSourceMap();
        for (var topic : annotation.topics()) {
            var id = topic.id();
            var topicName = topic.topicName();
            var brokerName = topic.brokerName();
            var consumerGroup = topic.consumerGroup();
//            var offsetStrategy = topic.offsetStrategy();
            var brokers = configMap.get(String.format("kafka.%s.brokers", brokerName));
            var source = org.apache.flink.connector.kafka.source.KafkaSource.<String>builder()
                    .setBootstrapServers(brokers)
                    .setTopics(topicName)
                    .setGroupId(consumerGroup)
                    .setValueOnlyDeserializer(new SimpleStringSchema())
                    .setProperties(props)
//                    .setStartingOffsets(offsetStrategy)
                    .build();
            var streamSource = env
                    .fromSource(source, WatermarkStrategy.noWatermarks(), consumerGroup);
            kafkaSourceMap.put(id, streamSource);
        }
        context.setKafkaSourceMap(kafkaSourceMap);

//        System.out.println("!");

//        var method = ((MethodSignature) thisJoinPoint.getSignature()).getMethod();
//
//        // 获取注解是在运行期间，必须是@Retention(RetentionPolicy.RUNTIME)的注解才能get到
//        var kafkaSourceAnnotation = method.getAnnotation(KafkaSource.class);
//        var topics = kafkaSourceAnnotation.topics();
//        for (var topic : topics) {
//            var id = topic.id();
//            var topicName = topic.topicName();
//
//        }

//        System.out.println("!");

//        task.setContext(context);
    }


}
