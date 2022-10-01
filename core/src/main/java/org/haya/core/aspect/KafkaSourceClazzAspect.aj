package org.haya.core.aspect;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.haya.core.Context;
import org.haya.core.anno.kafka.clazz.KafkaSource;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public aspect KafkaSourceClazzAspect {

    pointcut all():execution(* *(..));

    pointcut kafkaSourcePoint(org.haya.core.anno.kafka.clazz.KafkaSource kafkaSourceAnnotation): @annotation(kafkaSourceAnnotation);

    pointcut processMethod(): execution(public * org.haya.core.task.AbstractTask.process(..));


    before(org.haya.core.anno.kafka.clazz.KafkaSource annotation): all() && kafkaSourcePoint(annotation) && processMethod(){
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
        try {
            var kafkaSourceConfig = annotation.configClass()
                    .getDeclaredConstructor()
                    .newInstance();
            for (var topic : kafkaSourceConfig.getTopicList()) {
                var id = topic.getId();
                var topicName = topic.getTopicName();
                var brokerName = topic.getBrokerName();
                var consumerGroup = topic.getConsumerGroup();
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
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
        context.setKafkaSourceMap(kafkaSourceMap);

    }


}
