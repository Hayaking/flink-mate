package org.haya.core;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private static final Context instance = new Context();
    private StreamExecutionEnvironment streamEnv;
    private Map<String, DataStream<String>> kafkaSourceMap = new HashMap<>();


    public static Context getInstance() {
        return instance;
    }

    public StreamExecutionEnvironment getStreamEnv() {
        return streamEnv;
    }

    public void setStreamEnv(StreamExecutionEnvironment streamEnv) {
        this.streamEnv = streamEnv;
    }

    public Map<String, DataStream<String>> getKafkaSourceMap() {
        return kafkaSourceMap;
    }

    public void setKafkaSourceMap(Map<String, DataStream<String>> kafkaSourceMap) {
        this.kafkaSourceMap = kafkaSourceMap;
    }
}
