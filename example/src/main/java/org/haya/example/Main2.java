package org.haya.example;

import org.haya.core.anno.profile.clazz.ProfileList;
import org.haya.example.config.AppProfileConfig;
import org.haya.example.task.Task002;

public class Main2 {

    public static void main(String[] args) {
        run();
    }

    @ProfileList(configClass = AppProfileConfig.class)
    public static void run() {
        new Task002().process();
    }
}
