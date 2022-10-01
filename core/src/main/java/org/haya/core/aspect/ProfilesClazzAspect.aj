package org.haya.core.aspect;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.haya.core.Context;
import org.haya.core.anno.profile.clazz.ProfileList;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public aspect ProfilesClazzAspect {

    pointcut all():execution(* *(..));

    pointcut profilesAnnotation(ProfileList profilesAnnotation): @annotation(profilesAnnotation);


    before(ProfileList annotation): all() && profilesAnnotation(annotation){
        var env = StreamExecutionEnvironment.getExecutionEnvironment();
        var context = Context.getInstance();
        context.setStreamEnv(env);
        try {
            var profileListConfig = annotation.configClass()
                    .getDeclaredConstructor()
                    .newInstance();
            var profileList = profileListConfig.getProfileList();
            for (var profile : profileList) {
                if (!"dev".equals(profile.getEnv())) {
                    continue;
                }
                var map = new HashMap<String, String>();
                for (var item : profile.getKafkaConfigs()) {

                    var name = item.getName();
                    var brokers = item.getBrokers();
                    var offsetStrategy = item.getOffsetStrategy();
                    map.put(String.format("kafka.%s.brokers", name), brokers);
                    map.put(String.format("kafka.%s.offsetStrategy", name), offsetStrategy);
                }
                env.getConfig()
                        .setGlobalJobParameters(ParameterTool.fromMap(map));

            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }


    after(ProfileList annotation): all() && profilesAnnotation(annotation){
        try {
            System.out.println(Context.getInstance().getStreamEnv());
            Context.getInstance().getStreamEnv().execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
