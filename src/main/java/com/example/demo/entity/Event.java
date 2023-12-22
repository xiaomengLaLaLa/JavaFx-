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

    public Event() {
    }

    public Event(int id, LocalDateTime dateTime, String shipName, String userName, int residenceTime, String desc, int unit) {
        this.id = id;
        this.dateTime = dateTime;
        this.shipName = shipName;
        this.userName = userName;
        this.residenceTime = residenceTime;
        this.desc = desc;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", shipName='" + shipName + '\'' +
                ", userName='" + userName + '\'' +
                ", residenceTime=" + residenceTime +
                ", desc='" + desc + '\'' +
                ", unit=" + unit +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(int residenceTime) {
        this.residenceTime = residenceTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}