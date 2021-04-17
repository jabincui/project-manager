package com.buptcnc.projectmanager.config;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Constant {
    // security
    public static final String[] PROTECTED_URL = {"/prof_index"};
    public static final String[] HAS_ROLE = {"PROF"};

    /**
     * REST API Post返回状态参数
     */
    // 要CRUD的实体不合法
    public static final String INVALID = "INVALID_BODY";
    // 通过CRUD
    public static final String PASS = "PASS";
    // 要insert的实体已经存在
    public static final String EXIST = "EXIST";


//    /**
//     * [弃用]
//     * POJO实体状态常数命名约定：{pojo}_{state}
//     * state空间
//     *      PREPARE 未开始/未完成
//     *      GOING 工作中
//     *      DONE 已结束/已完成
//     */
//    // ------------------------ Project -----------------------------
//    public static final String PROJ_PREPARE = "0";
//    public static final String PROJ_GOING = "1";
//    public static final String PROJ_DONE = "2";
//
//    // ------------------------ Code -----------------------------
//    public static final String CODE_PREPARE = "0";
//    public static final String CODE_DONE = "2";

    /**
     * POJO实体状态常数命名约定：STA_{state}
     * state空间
     *      PREPARE 未开始/未完成
     *      GOING 工作中
     *      DONE 已结束/已完成
     */
    public static final String STA_PREPARE = "0";
    public static final String STA_GOING = "1";
    public static final String STA_DONE = "2";


    public static final String ROLE_USER = "USER";



    public static final String SIGN_IN_SUCCESS = "2000";


    /**
     * JAVA测试
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        long l = 1618558906679L;
        Date date = new Date();
        date.setTime(l);
        System.out.println(date);
    }
}
