package com.buptcnc.projectmanager.util;

/**
 * 实时消息同步
 * 前端每10秒向后端发送ajax，
 * 如果是第一次，就把消息推给前端，并且记录当前时间
 * 如果不是第一次，就把上一次时间后出现的消息推给前端
 *
 * 太难了先不实现，每次前端请求时都把全部消息推过去
 */
public class RealtimeMessage {
}
