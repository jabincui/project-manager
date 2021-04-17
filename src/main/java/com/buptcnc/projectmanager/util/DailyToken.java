package com.buptcnc.projectmanager.util;

/**
 * 每天更新的token，用于验证一些安全操作
 * 如创建账户
 */
public class DailyToken {
    private static final String SEED = "buptcnc";

    public static String getToken(){
        return md5(SEED);
    }

    // TODO
    private static String md5(String seed){
        return seed;
    }

}
