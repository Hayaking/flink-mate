package org.haya.core.task;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.haya.core.Context;

public class AbstractTask {

    public DataStream<String> process() {
        return null;
    }

    public Context getContext() {
        return Context.getInstance();
    }

}
