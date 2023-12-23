package com.example.demo.entity;


import java.time.LocalDate;
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
    private LocalDate dateTime;
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
    private String unit;

    public Event() {
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
                ", unit='" + unit + '\'' +
                '}';
    }

    public Event(int id, LocalDate dateTime, String shipName, String userName, int residenceTime, String desc, String unit) {
        this.id = id;
        this.dateTime = dateTime;
        this.shipName = shipName;
        this.userName = userName;
        this.residenceTime = residenceTime;
        this.desc = desc;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public String getShipName() {
        return shipName;
    }

    public String getUserName() {
        return userName;
    }

    public int getResidenceTime() {
        return residenceTime;
    }

    public String getDesc() {
        return desc;
    }

    public String getUnit() {
        return unit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setResidenceTime(int residenceTime) {
        this.residenceTime = residenceTime;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}