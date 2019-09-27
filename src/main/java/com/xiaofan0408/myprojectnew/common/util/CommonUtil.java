package com.xiaofan0408.myprojectnew.common.util;

import java.util.concurrent.ForkJoinPool;

/**
 * @author xuzefan  2019/7/26 10:43
 */
public class CommonUtil {

    private static ForkJoinPool commonTaskPool = new ForkJoinPool(1024,ForkJoinPool.defaultForkJoinWorkerThreadFactory,null,true);

    public static ForkJoinPool getCommonTaskPool() {
        if (commonTaskPool == null) {
            synchronized (CommonUtil.class){
                commonTaskPool = new ForkJoinPool(1024,ForkJoinPool.defaultForkJoinWorkerThreadFactory,null,true);
            }
        }
        return commonTaskPool;
    }
}
