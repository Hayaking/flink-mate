package org.haya.core.anno.kafka.clazz;

public class KafkaBroker {
    private String name;

    private String brokers;

    private String offsetStrategy;

    public String getName() {
        return name;
    }

    public KafkaBroker setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrokers() {
        return brokers;
    }

    public KafkaBroker setBrokers(String brokers) {
        this.brokers = brokers;
        return this;
    }

    public String getOffsetStrategy() {
        return offsetStrategy;
    }

    public KafkaBroker setOffsetStrategy(String offsetStrategy) {
        this.offsetStrategy = offsetStrategy;
        return this;
    }
}
