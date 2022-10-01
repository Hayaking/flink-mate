package org.haya.core.filter;

public class NullKafkaSourceFilter implements KafkaSourceFilter <Object, Object>{
    @Override
    public Object filter(Object input) {
        return null;
    }
}
