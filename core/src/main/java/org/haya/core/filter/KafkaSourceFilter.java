package org.haya.core.filter;

public interface KafkaSourceFilter<I, O> {
    O filter(I input);
}
