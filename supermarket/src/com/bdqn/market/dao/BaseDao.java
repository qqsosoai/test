package com.bdqn.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bdqn.market.util.ConfigManger;

public class BaseDao {
	/**
	 * 获取数据库连接方法
	 * @return数据库连接对象
	 */
	public static Connection getConnection(){
		Connection connection=null;
		String url=ConfigManger.getInstance().getValue("jdbc.url");
		String driver=ConfigManger.getInstance().getValue("jdbc.driver");
		String name=ConfigManger.getInstance().getValue("jdbc.name");
		String password=ConfigManger.getInstance().getValue("jdbc.password");
		try {
			Class.forName(driver);
			connection=DriverManager.getConnection(url, name, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * 执行DQL语句方法
	 * @param connection数据库连接对象
	 * @param ps语句执行对象
	 * @param rs结果集对象
	 * @param sql语句
	 * @param params参数
	 * @return结果集对象
	 * @throws SQLException
	 */
	public static ResultSet execute(Connection connection,PreparedStatement ps,ResultSet rs,String sql,Object ...params) throws SQLException{
		ps=connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i+1, params[i]);
		}
		rs=ps.executeQuery();
		return rs;
	}
	/**
	 * 执行DML语句
	 * @param connection数据库连接对象
	 * @param ps语句执行对象
	 * @param sql语句
	 * @param params参数
	 * @return影响行数
	 * @throws SQLException
	 */
	public static int execute(Connection connection,PreparedStatement ps,String sql,Object ...params) throws SQLException{
		ps=connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i+1, params[i]);
		}
		int updateRows=ps.executeUpdate();
		return updateRows;
	}
	/**
	 * 关闭数据库连接
	 * @param connection数据库连接
	 * @param ps语句执行对象
	 * @param rs结果集对象
	 * @return
	 */
	public static boolean closeConnection(Connection connection,PreparedStatement ps,ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (ps!=null) {
			try {
				ps.close();
				ps=null;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (connection!=null) {
			try {
				connection.close();
				connection=null;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
