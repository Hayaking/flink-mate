package org.haya.core.anno.profile.clazz;

import org.haya.core.anno.kafka.clazz.KafkaBroker;
import org.haya.core.anno.redis.Redis;

public class ProfileConfig {
    private String env;
    private KafkaBroker[] kafkaConfigs;
    private Redis[] redisConfigs;


    public String getEnv() {
        return env;
    }

    public ProfileConfig setEnv(String env) {
        this.env = env;
        return this;
    }

    public KafkaBroker[] getKafkaConfigs() {
        return kafkaConfigs;
    }

    public ProfileConfig setKafkaConfigs(KafkaBroker[] kafkaConfigs) {
        this.kafkaConfigs = kafkaConfigs;
        return this;
    }

    public Redis[] getRedisConfigs() {
        return redisConfigs;
    }

    public ProfileConfig setRedisConfigs(Redis[] redisConfigs) {
        this.redisConfigs = redisConfigs;
        return this;
    }
}
