package com.example.demo.utils;


import java.sql.*;

public class DBUtil {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 得到数据库连接
	 */
	public Connection getConn() {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String URL = "jdbc:mysql://114.115.140.251:3306/XiaoMeng?&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String USER="root";
		String PASSWORD="zhangruixin";
		// 注册驱动
		try {
			Class.forName(DRIVER);
			// 获得数据库连接
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 返回连接
		return conn;

	}

	/**
	 * 释放资源
	 */
	public void closeAll() {
		// 如果rs不空，关闭rs
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 如果pstmt不空，关闭pstmt
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 如果conn不空，关闭conn
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行SQL语句，可以进行查询
	 */
	public ResultSet executeQuery(String preparedSql, String[] param) {
		// 处理SQL,执行SQL
		try {
			// 得到PreparedStatement对象
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null && param.length>0) {
				for (int i = 0; i < param.length; i++) {
					// 为预编译sql设置参数
					pstmt.setString(i + 1, param[i]);
				}
			}
			// 执行SQL语句
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// 处理SQLException异常
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 执行SQL语句，可以进行增、删、改的操作，不能执行查询
	 */
	public int executeUpdate(String preparedSql, String[] param) {

		int num = 0;

		// 处理SQL,执行SQL
		try {
			// 得到PreparedStatement对象
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					// 为预编译sql设置参数
					pstmt.setString(i + 1, param[i]);
				}
			}
			// 执行SQL语句
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			// 处理SQLException异常
			e.printStackTrace();
		}
		return num;
	}


}
