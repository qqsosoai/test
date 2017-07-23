package com.bdqn.market.service.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.bdqn.market.bean.User;
import com.bdqn.market.dao.BaseDao;
import com.bdqn.market.dao.bill.BillDao;
import com.bdqn.market.dao.bill.impl.BillDaoImpl;
import com.bdqn.market.dao.provider.ProviderDao;
import com.bdqn.market.dao.provider.impl.ProviderDaoImpl;
import com.bdqn.market.dao.user.UserDao;
import com.bdqn.market.dao.user.impl.UserDaoImpl;
import com.bdqn.market.service.user.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private BillDao billDao;
	private ProviderDao providerDao;
	public UserServiceImpl(){
		if (userDao==null) {
			userDao=new UserDaoImpl();
		}
		if (billDao==null) {
			billDao=new BillDaoImpl();
		}
		if (providerDao==null) {
			providerDao=new ProviderDaoImpl();
		}
	}

	@Override//根据用户id查询集体信息
	public User select(int id) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		try {
			user=userDao.select(connection, ps, rs, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeConnection(connection, ps, rs);
		}
		return user;
	}

	@Override//新增用户
	public boolean insert(User t) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);
			int updateRows=userDao.insert(connection, ps, t);
			if (updateRows>0) {
				flag=true;
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			BaseDao.closeConnection(connection, ps, null);
		}
		return flag;
	}

	@Override//删除用户
	public boolean delete(int id) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);
			int updateRows=userDao.delete(connection, ps, id);
			if (updateRows>0) {
				flag=true;
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			BaseDao.closeConnection(connection, ps, null);
		}
		
		return flag;
	}

	@Override//更改用户信息
	public boolean update(User t) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);//设置开启事务
			int updateRows=0;
			if (t.getUserType()!=null) {//判断用户类型是否为空
				updateRows=userDao.update(connection, ps, t);
			}else {//用户类型为空调用非修改用户类型的修改方法
				updateRows=userDao.updateNotType(connection, ps, t);
			}
			if (updateRows>0) {
				flag=true;
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			BaseDao.closeConnection(connection, ps, null);
		}
		return flag;
	}

	@Override//修改用户密码
	public boolean update(int id,String password) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);
			int updateRows=userDao.update(connection, ps, id, password);
			if (updateRows>0) {
				flag=true;
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			BaseDao.closeConnection(connection, ps, null);
		}
		return flag;
	}

	@Override//根据账号查询用户
	public User login(String name) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		try {
			user=userDao.login(connection, ps, rs, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeConnection(connection, ps, rs);
		}
		return user;
	}

	@Override//根据用户输入条件查询并分页
	public List<User> selectForName(String name, Integer pageIndex,
			Integer pageSize) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<User>list=null;
		try {
			list=userDao.select(connection, ps, rs, name, pageIndex, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeConnection(connection, null, null);
		}
		return list;
	}
	@Override//根据用户输入查询总记录数
	public int selectCount(String name) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try {
			count=userDao.selectCount(connection, ps, rs, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeConnection(connection, null, null);
		}
		return count;
	}
	
	@Override//判断是否能删除
	public int delete(Integer loginId, Integer delId) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int rowsUpdate=0;
		try {
			User loginUser=userDao.select(connection, ps, rs, loginId);
			User delUser=userDao.select(connection, ps, rs, delId);
			if (loginUser.getUserType()<=delUser.getUserType()) {//判断是否有权限删除
				rowsUpdate=userDao.delete(connection, ps, delId);
			}else {
				rowsUpdate=-1;//代表没有权限删除
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdate;
	}
	@Override//查询所有
	public List<User> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//查询所有总记录数
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> select(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
