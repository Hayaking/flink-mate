package org.haya.example;

import org.haya.core.anno.kafka.anno.KafkaBroker;
import org.haya.core.anno.profile.anno.Profile;
import org.haya.core.anno.profile.anno.ProfileList;
import org.haya.example.task.Task001;

public class Main {
    public static void main(String[] args) {
        run();
    }

    @ProfileList(profileList = {
            @Profile(env = "dev",
                    kafkaConfigs = {
                            @KafkaBroker(name = "haya", brokers = "kafka-service.kafka:9092"),
                            @KafkaBroker(name = "2", brokers = "kafka-service.kafka:9092"),
                    }
            ),
            @Profile(env = "prod",
                    kafkaConfigs = {
                            @KafkaBroker(name = "", brokers = ""),
                            @KafkaBroker(name = "", brokers = ""),
                    }
            )
    })
    public static void run() {
        new Task001().process();
    }

}
