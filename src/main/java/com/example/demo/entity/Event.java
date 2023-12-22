package com.example.demo.entity;


import java.time.LocalDateTime;

/**
 * @author lm
 * @date 2023/12/22 19:21
 */
public class Event {
    /**
     * 主键
     */
    private int id;
    /**
     * 事件的触发时间
     */
    private LocalDateTime dateTime;
    /**
     * 飞船名
     */
    private String shipName;
    /**
     * 成员名
     */
    private String userName;
    /**
     * 驻留时间
     */
    private int residenceTime;
    /**
     * 详情描述
     */
    private String desc;
    /**
     * 单位
     */
    private int unit;
}