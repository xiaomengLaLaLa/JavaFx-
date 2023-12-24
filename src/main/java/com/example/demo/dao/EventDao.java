package com.example.demo.dao;

import com.example.demo.MainApplication;
import com.example.demo.entity.Event;
import com.example.demo.utils.DBUtil;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lm
 * @since 2023/12/24 1:09
 */
public interface EventDao {

    /**
     * 新增
     * @param event
     */
    public int add(Event event);

    /**
     * 删除数据
     */
    public void delete(String id);

    /**
     * 修改数据
     */
    public int update(Event event);

    /**
     * 查询数据
     */
    public List<Event> findAll() throws Exception;
}
