package com.bdqn.market.dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bdqn.market.bean.User;
import com.bdqn.market.dao.BaseDao;
import com.bdqn.market.dao.user.UserDao;
import java.sql.Timestamp;

public class UserDaoImpl implements UserDao {
	//已测试
	@Override//查询所有用户
	public List<User> select(Connection connection, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		String sql="select * from smbms_user order by id desc";
		List<User>list=new ArrayList<User>();
		rs=BaseDao.execute(connection, ps, rs, sql);
		while (rs.next()) {
			User user=new User(rs.getInt("id"), rs.getString("userCode"), rs.getString("userName"),
					rs.getString("userPassword"), rs.getInt("gender"), rs.getDate("birthday"), rs.getString("phone")
					, rs.getString("address"), rs.getInt("userType"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"));
			list.add(user);
		}
		BaseDao.closeConnection(null, ps, rs);
		return list;
	}
	//已测试
	@Override//根据ID查询具体用户
	public User select(Connection connection, PreparedStatement ps,
			ResultSet rs, int id) throws SQLException {
		User user=null;
		String sql="select * from smbms_user where id=?";
		Object[] params={id};
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		if (rs.next()) {
			user=new User(rs.getInt("id"), rs.getString("userCode"), rs.getString("userName"),
					rs.getString("userPassword"), rs.getInt("gender"), rs.getDate("birthday"), rs.getString("phone")
					, rs.getString("address"), rs.getInt("userType"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"));
		}
		BaseDao.closeConnection(null, ps, rs);
		return user;
	}
	//已测试
	@Override//更新用户
	public int update(Connection connection, PreparedStatement ps, User e)
			throws SQLException {
		String sql="update smbms_user set userName=?,gender=?,birthday=?,phone=?,address=?,userType=?," +
				"modifyBy=?,modifyDate=? where id=?";
		Object[] params={e.getUserName(),e.getGender(),e.getBirthday(),e.getPhone(),e.getAddress(),
				e.getUserType(),e.getModifyBy(),new Timestamp(e.getModifyDate().getTime()),e.getId()};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	//已测试
	@Override//新增用户
	public int insert(Connection connection, PreparedStatement ps,
			User e) throws SQLException {
		String sql="insert into smbms_user(userCode,userName,userPassword,gender,birthday,phone,address,userType," +
				"createdBy,creationDate)values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params={e.getUserCode(),e.getUserName(),e.getUserPassword(),e.getGender(),e.getBirthday(),
				e.getPhone(),e.getAddress(),e.getUserType(),e.getCreatedBy(),
				new Timestamp(e.getCreationDate().getTime())};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	//已测试
	@Override//根据ID删除用户
	public int delete(Connection connection, PreparedStatement ps, int id)
			throws SQLException {
		String sql="delete from smbms_user where id=?";
		Object[] params={id};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	//已测试
	@Override//根据用户账号查询用户
	public User login(Connection connection, PreparedStatement ps,
			ResultSet rs, String userName) throws SQLException {
		String sql="select * from smbms_user where userCode=?";
		Object[] params={userName};
		User user=null;
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		if (rs.next()) {
			user=new User(rs.getInt("id"), rs.getString("userCode"), rs.getString("userName"),
					rs.getString("userPassword"), rs.getInt("gender"), rs.getDate("birthday"), rs.getString("phone")
					, rs.getString("address"), rs.getInt("userType"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"));
		}
		BaseDao.closeConnection(null, ps, rs);
		return user;
	}
	//已测试
	@Override//更新用户密码
	public int update(Connection connection, PreparedStatement ps, int id,
			String password) throws SQLException {
		String sql="update smbms_user set userPassword=? where id=?";
		Object[] params={password,id};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	@Override//查询数据库记录数
	public int selectCount(Connection connection, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		String sql="select count(1) from smbms_user";
		int count=0;
		rs=BaseDao.execute(connection, ps, rs, sql);
		if (rs.next()) {
			count=rs.getInt(1);
		}
		BaseDao.closeConnection(null, ps, rs);
		return count;
	}
	@Override//按条件分页查询
	public List<User> select(Connection connection, PreparedStatement ps,
			ResultSet rs, String condition, Integer pageIndex, Integer pageSize)
			throws SQLException {
		StringBuffer sql=new StringBuffer("select * from smbms_user");
		List<Object> params=new ArrayList<Object>();
		List<User>list=new ArrayList<User>();
		if (condition!="" && condition!=null) {//判断用户是有输入条件
			sql.append(" where userName like ? ");
			if (condition.indexOf("%")>-1 || condition.indexOf("\\")>-1) {
				condition="\\"+condition;
			}
			params.add("%"+condition+"%");
		}
		if (pageIndex!=null && pageSize!=null) {
			sql.append(" limit ?,? ");
			params.add((pageIndex-1)*pageSize);
			params.add(pageSize);
		}
		rs=BaseDao.execute(connection, ps, rs, sql.toString(), params.toArray());
		while (rs.next()) {
			User user=new User(rs.getInt("id"), rs.getString("userCode"), rs.getString("userName"),
					rs.getString("userPassword"), rs.getInt("gender"), rs.getDate("birthday"), rs.getString("phone")
					, rs.getString("address"), rs.getInt("userType"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"));
			list.add(user);
		}
		BaseDao.closeConnection(null, ps, rs);
		return list;
	}
	@Override
	public int selectCount(Connection connection, PreparedStatement ps,
			ResultSet rs, String name)throws SQLException {
		StringBuffer sql=new StringBuffer("select count(1) from smbms_user");
		List<Object> params=new ArrayList<Object>();
		int count=0;
		if (name!="" && name!=null) {//判断用户是有输入条件
			sql.append(" where userName like ? ");
			if (name.indexOf("%")>-1 || name.indexOf("\\")>-1) {
				name="\\"+name;
			}
			params.add("%"+name+"%");
		}
		rs=BaseDao.execute(connection, ps, rs, sql.toString(), params.toArray());
		if (rs.next()) {
			count=rs.getInt(1);
		}
		BaseDao.closeConnection(null, ps, rs);
		return count;
	}
	@Override
	public int updateNotType(Connection connection, PreparedStatement ps,
			User e) throws SQLException {
		String sql="update smbms_user set userName=?,gender=?,birthday=?,phone=?,address=?," +
				"modifyBy=?,modifyDate=? where id=?";
		Object[] params={e.getUserName(),e.getGender(),e.getBirthday(),e.getPhone(),e.getAddress(),
				e.getModifyBy(),new Timestamp(e.getModifyDate().getTime()),e.getId()};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		return updateRows;
	}


}
