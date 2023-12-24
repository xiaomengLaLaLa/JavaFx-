package com.example.demo.dao;

import com.example.demo.MainApplication;
import com.example.demo.entity.Event;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zrx
 * @since 2023/12/24 1:09
 */
public class EventDao {

    /**
     * 新增
     * @param event
     */
    public int add(Event event) {
        return MainApplication.dbUtilInstance.executeUpdate(
                "insert into event(datetime, shipName, userName, residenceTime, `desc`, unit) values(?,?,?,?,?,?)"
                , new String[]{event.getDateTime().toString()
                        , event.getShipName()
                        , event.getUserName(),
                        String.valueOf(event.getResidenceTime()),
                        event.getDesc(),
                        event.getUnit()});
    }

    /**
     * 删除数据
     */
    public void delete(String id) {
        String deleteQuery = "DELETE FROM event WHERE id = ?";
        MainApplication.dbUtilInstance.executeUpdate(deleteQuery, new String[]{String.valueOf(id)});
    }

    /**
     * 修改数据
     */
    public int update(Event event) {
        String updateQuery = "UPDATE event SET dateTime = ?, shipName = ?, userName = ?, residenceTime = ?, `desc` = ?, unit = ? WHERE id = ?";
        return MainApplication.dbUtilInstance.executeUpdate(updateQuery, new String[]{String.valueOf(event.getDateTime()),
                event.getShipName(),
                event.getUserName(),
                String.valueOf(event.getResidenceTime()),
                event.getDesc(),
                event.getUnit(),
                String.valueOf(event.getId())});
    }

    /**
     * 查询数据
     */
    public List<Event> findAll() throws Exception {
        long startTime = System.nanoTime();
        // 不通过单例的查询，每次查询都重新连接数据库
//        DBUtil dbUtil = new DBUtil();
//        dbUtil.getConn();
//        ResultSet rs = dbUtil.executeQuery("SELECT * FROM event", null);
//        List<Event> events = new ArrayList<>();
//        while (rs.next()) {
//            Event event = new Event();
//            event.setId(Integer.parseInt(rs.getString("id")));
//            event.setDateTime(LocalDate.parse(rs.getString("dateTime").substring(0, 10)));
//            event.setShipName(rs.getString("shipName"));
//            event.setUserName(rs.getString("userName"));
//            event.setResidenceTime(Integer.parseInt(rs.getString("residenceTime")));
//            event.setDesc(rs.getString("desc"));
//            event.setUnit(rs.getString("unit"));
//
//            events.add(event);
//        }
//        dbUtil.closeAll();
//        System.out.println("执行了一次查询");
//        long endTime = System.nanoTime();
//        long duration = endTime - startTime; // 计算执行时间（单位：纳秒）
//
//        // 将纳秒转换为毫秒或秒（如果需要）
//        double durationInMs = (double) duration / 1000000;
//        double durationInSeconds = (double) durationInMs / 1000;
//
//        System.out.println("Method execution time in milliseconds: " + durationInMs);
//        System.out.println("Method execution time in seconds: " + durationInSeconds);
//
//        return events;
        // 优化
        String query = "SELECT * FROM event";
        ResultSet rs = MainApplication.dbUtilInstance.executeQuery(query, new String[]{});
        List<Event> events = new ArrayList<>();
        while (rs.next()) {
            Event event = new Event();
            event.setId(Integer.parseInt(rs.getString("id")));
            event.setDateTime(LocalDate.parse(rs.getString("dateTime").substring(0, 10)));
            event.setShipName(rs.getString("shipName"));
            event.setUserName(rs.getString("userName"));
            event.setResidenceTime(Integer.parseInt(rs.getString("residenceTime")));
            event.setDesc(rs.getString("desc"));
            event.setUnit(rs.getString("unit"));

            events.add(event);
        }
        System.out.println("执行了一次查询");
        long endTime = System.nanoTime();
        long duration = endTime - startTime; // 计算执行时间（单位：纳秒）

        // 将纳秒转换为毫秒或秒（如果需要）
        double durationInMs = (double) duration / 1000000;
        double durationInSeconds = (double) durationInMs / 1000;

        System.out.println("Method execution time in milliseconds: " + durationInMs);
        System.out.println("Method execution time in seconds: " + durationInSeconds);
        return events;

    }
}
