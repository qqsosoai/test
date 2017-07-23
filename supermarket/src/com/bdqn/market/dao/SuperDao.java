package com.bdqn.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bdqn.market.bean.Bill;

public interface SuperDao<E> {
	/**
	 * 查询所有表数据
	 * @return
	 */
	List<E> select(Connection connection,PreparedStatement ps,ResultSet rs) throws SQLException;
	/**
	 * 根据用户输入查询表数据
	 * @param condition用户条件
	 * @return
	 */
	List<E> select(Connection connection,PreparedStatement ps,ResultSet rs,String condition,Integer pageIndex,Integer pageSize)throws SQLException;
	/**
	 * 根据id查询集体数据
	 * @param id
	 * @return返回表对象
	 */
	E select(Connection connection,PreparedStatement ps,ResultSet rs,int id)throws SQLException;
	/**
	 * 查询总记录数
	 * @param connection数据库连接对象
	 * @param ps语句执行对象
	 * @param rs结果集
	 * @return总记录数
	 * @throws SQLException
	 */
	int selectCount(Connection connection,PreparedStatement ps,ResultSet rs)throws SQLException;
	/**
	 * 修改表
	 * @param connection数据库连接
	 * @param ps语句执行对象
	 * @param e实体类对象
	 * @return影响行数
	 * @throws SQLException
	 */
	int update(Connection connection,PreparedStatement ps,E e)throws SQLException;
	/**
	 * 新增表数据
	 * @param E 表对象
	 * @return影响行数
	 */
	int insert(Connection connection,PreparedStatement ps,E e)throws SQLException;
	/**
	 * 删除表对象
	 * @param id 表对象ID
	 * @return影响行数
	 */
	int delete(Connection connection,PreparedStatement ps,int id)throws SQLException;
}
