package com.xbky.circuitManger.utils;

import java.util.Random;

public class IdMaker {

    public static final IdMaker instance = new IdMaker();

    /**
     * 每个毫秒重置一次
     */
    private long currentMillis;

    private int num;

    private IdMaker() {
    }

    public synchronized long getLongId() {
        long time = System.currentTimeMillis();
        // 每天重新开始计数
        if (this.currentMillis != time) {
            this.currentMillis = time;
            this.num = new Random().nextInt(100);
        }
        long seq = 999L;
        // 未来100年time的长度都不会超过42个字节长度
        // Long的高4位保留，5-14保存seq(所以最大为2^10-1=1023)，15-56保存time，57-64保存num
        // 前提条件是每毫秒只能生成2^8-1=255(还要减去前面的随机值)个ID，应用不可能超过
        time = seq << 50 | time << 8 | this.num++;
        return time;
    }
    public String getStringId()
    {
        return String.valueOf(getLongId());
    }

    public String getStringHexId()
    {
        return Long.toHexString(getLongId());
    }

    public String getStringId(String prefix)
    {
        return prefix+String.valueOf(getLongId());
    }
}