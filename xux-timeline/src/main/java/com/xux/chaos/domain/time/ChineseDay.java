package com.xux.chaos.domain.time;

import java.util.HashSet;
import java.util.Set;

public class ChineseDay {
    private static final Set<String> HeavenlyStems = new HashSet(10);
    private static final Set<String> EarthlyBranches = new HashSet(12);
    static {
        HeavenlyStems.add("阏逢");
        HeavenlyStems.add("旃蒙");
        HeavenlyStems.add("柔兆");
        HeavenlyStems.add("强圉");
        HeavenlyStems.add("著雍");
        HeavenlyStems.add("屠维");
        HeavenlyStems.add("上章");
        HeavenlyStems.add("重光");
        HeavenlyStems.add("玄黓");
        HeavenlyStems.add("昭阳");

        EarthlyBranches.add("困顿");
        EarthlyBranches.add("赤奋若");
        EarthlyBranches.add("摄提格");
        EarthlyBranches.add("单阏");
        EarthlyBranches.add("执徐");
        EarthlyBranches.add("大荒落");
        EarthlyBranches.add("敦牂");
        EarthlyBranches.add("协洽");
        EarthlyBranches.add("涒滩");
        EarthlyBranches.add("作噩");
        EarthlyBranches.add("阉茂");
        EarthlyBranches.add("大渊献");
    }
}
