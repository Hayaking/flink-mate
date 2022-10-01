package org.haya.core.aspect;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.haya.core.Context;

import java.util.Arrays;
import java.util.HashMap;

public aspect ProfilesAnnoAspect {

    pointcut all():execution(* *(..));

    pointcut profilesAnnotation(org.haya.core.anno.profile.anno.ProfileList profilesAnnotation): @annotation(profilesAnnotation);


    before(org.haya.core.anno.profile.anno.ProfileList annotation): all() && profilesAnnotation(annotation){
        var env = StreamExecutionEnvironment.getExecutionEnvironment();
        var context = Context.getInstance();
        context.setStreamEnv(env);
        Arrays.stream(annotation.profileList())
                .filter(item -> "dev".equals(item.env()))
                .findFirst()
                .ifPresent(profile -> {
                    var map = new HashMap<String, String>();
                    for (var item : profile.kafkaConfigs()) {
                        var name = item.name();
                        var brokers = item.brokers();
                        var offsetStrategy = item.offsetStrategy();
                        map.put(String.format("kafka.%s.brokers", name), brokers);
                        map.put(String.format("kafka.%s.offsetStrategy", name), offsetStrategy);
                    }
                    env.getConfig()
                            .setGlobalJobParameters(ParameterTool.fromMap(map));

                });


    }


    after(org.haya.core.anno.profile.anno.ProfileList annotation): all() && profilesAnnotation(annotation){
        try {
            Context.getInstance().getStreamEnv().execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
