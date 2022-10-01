package org.haya.example.config;

import org.haya.core.anno.kafka.clazz.KafkaBroker;
import org.haya.core.anno.profile.clazz.ProfileConfig;
import org.haya.core.anno.profile.clazz.ProfileListConfig;

import java.util.List;

public class AppProfileConfig implements ProfileListConfig {
    @Override
    public List<ProfileConfig> getProfileList() {
        return List.of(
                new ProfileConfig()
                        .setEnv("dev")
                        .setKafkaConfigs(new KafkaBroker[]{
                                new KafkaBroker()
                                        .setName("kafka-1")
                                        .setBrokers("kafka-service.kafka:9092"),
                                new KafkaBroker()
                                        .setName("kafka-2")
                                        .setBrokers("kafka-service.kafka:9092"),
                        }),
                new ProfileConfig()
                        .setEnv("prod")
                        .setKafkaConfigs(new KafkaBroker[]{
                                new KafkaBroker()
                                        .setName("kafka-1")
                                        .setBrokers("kafka-service.kafka:9092"),
                                new KafkaBroker()
                                        .setName("kafka-2")
                                        .setBrokers("kafka-service.kafka:9092"),
                        })
        );
    }

}
