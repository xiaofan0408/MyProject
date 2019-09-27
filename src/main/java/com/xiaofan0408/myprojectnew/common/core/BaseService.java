package com.xiaofan0408.myprojectnew.common.core;

import com.xiaofan0408.myprojectnew.common.snowflake.SnowflakeFactory;

/**
 * @author xuzefan  2019/9/25 10:10
 */
public abstract class BaseService<T> {

    public long generateKey() {
        return SnowflakeFactory.getSnowflakeIdWorker().nextId();
    }
}
