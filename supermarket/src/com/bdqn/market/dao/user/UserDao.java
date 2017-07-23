package com.bdqn.market.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bdqn.market.bean.Bill;
import com.bdqn.market.bean.User;
import com.bdqn.market.dao.SuperDao;

public interface UserDao extends SuperDao<User>{
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	User login(Connection connection,PreparedStatement ps,ResultSet rs,String userName)throws SQLException;
	/**
	 * 修改密码
	 * @param password新密码
	 * @return影响行数
	 */
	int update(Connection connection,PreparedStatement ps,int id,String password)throws SQLException;
	/**
	 * 根据用户输入查询总记录数
	 * @param connection
	 * @param ps
	 * @param rs
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	int selectCount(Connection connection,PreparedStatement ps,ResultSet rs,String name)throws SQLException;
	/**
	 * 不带用户类型的修改
	 * @param connection
	 * @param ps
	 * @param e
	 * @return
	 * @throws SQLException
	 */
	int updateNotType(Connection connection,PreparedStatement ps,User e)throws SQLException;
}
