package org.haya.core.anno.kafka.clazz;

import org.apache.kafka.common.serialization.StringSerializer;
import org.haya.core.filter.KafkaSourceFilter;
import org.haya.core.filter.NullKafkaSourceFilter;


public class KafkaSourceTopicConfig {

    private String id;

    private String topicName;

    private String brokerName;

    private  Class<?> serializer = StringSerializer.class;

    private  Class<?> deserializer = StringSerializer.class;

    private  Class<? extends KafkaSourceFilter> filter = NullKafkaSourceFilter.class;

    private  String consumerGroup = "";

    private  String offsetStrategy = "latest";


    public String getId() {
        return id;
    }

    public KafkaSourceTopicConfig setId(String id) {
        this.id = id;
        return this;
    }

    public String getTopicName() {
        return topicName;
    }

    public KafkaSourceTopicConfig setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public KafkaSourceTopicConfig setBrokerName(String brokerName) {
        this.brokerName = brokerName;
        return this;
    }

    public Class<?> getSerializer() {
        return serializer;
    }

    public KafkaSourceTopicConfig setSerializer(Class<?> serializer) {
        this.serializer = serializer;
        return this;
    }

    public Class<?> getDeserializer() {
        return deserializer;
    }

    public KafkaSourceTopicConfig setDeserializer(Class<?> deserializer) {
        this.deserializer = deserializer;
        return this;
    }

    public Class<? extends KafkaSourceFilter> getFilter() {
        return filter;
    }

    public KafkaSourceTopicConfig setFilter(Class<? extends KafkaSourceFilter> filter) {
        this.filter = filter;
        return this;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public KafkaSourceTopicConfig setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
        return this;
    }

    public String getOffsetStrategy() {
        return offsetStrategy;
    }

    public KafkaSourceTopicConfig setOffsetStrategy(String offsetStrategy) {
        this.offsetStrategy = offsetStrategy;
        return this;
    }
}
