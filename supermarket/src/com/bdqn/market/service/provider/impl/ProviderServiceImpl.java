package com.bdqn.market.service.provider.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bdqn.market.bean.Provider;
import com.bdqn.market.dao.BaseDao;
import com.bdqn.market.dao.bill.BillDao;
import com.bdqn.market.dao.bill.impl.BillDaoImpl;
import com.bdqn.market.dao.provider.ProviderDao;
import com.bdqn.market.dao.provider.impl.ProviderDaoImpl;
import com.bdqn.market.dao.user.UserDao;
import com.bdqn.market.dao.user.impl.UserDaoImpl;
import com.bdqn.market.service.provider.ProviderServicec;

public class ProviderServiceImpl implements ProviderServicec {
	private ProviderDao providerDao;
	private BillDao billDao;
	private UserDao userDao;
	public ProviderServiceImpl(){
		if (providerDao==null) {
			providerDao=new ProviderDaoImpl();
		}
		if (billDao==null) {
			billDao=new BillDaoImpl();
		}
		if (userDao==null) {
			userDao=new UserDaoImpl();
		}
	}
	@Override//根据ID查询
	public Provider select(int id) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Provider provider=null;
		try {
			provider=providerDao.select(connection, ps, rs, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeConnection(connection, ps, rs);
		}
		return provider;
	}
	
	@Override//新增供应商信息
	public boolean insert(Provider t) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);
			int updateRows=providerDao.insert(connection, ps, t);
			if (updateRows>0) {
				connection.commit();
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}

	@Override//删除供应商信息
	public boolean delete(int id) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);
			int count=billDao.selectIsPro(connection, ps, rs, id);
			if (count==0) {
				int updateRows=providerDao.delete(connection, ps, id);
				if (updateRows>0) {
					flag=true;
					connection.commit();
				}
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

	@Override//修改供应商表
	public boolean update(Provider t) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean flag=false;
		int updateRows=0;
		try {
			connection.setAutoCommit(false);
			Provider provider=providerDao.select(connection, ps, rs, t.getId());
			if (provider.getProName().equals(t.getProName())) {//代表用户没有修改供应商名称
				updateRows=providerDao.update(connection, ps, t);
			}else {//代表用户修改了供应商名称，要先同时成功修改供应商表与账单表
				//查询这个供应商是否有账单
				int count=billDao.selectProNameCount(connection, ps, rs, provider.getProName());
				if (count>0) {//该供应商有账单
					updateRows=billDao.update(connection, ps, provider.getProName(),t.getProName());
					if (updateRows>0) {//成功修改账单表供应商名称
						updateRows=providerDao.update(connection, ps, t);
					}
				}
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
	@Override//查询所有供应商信息
	public List<Provider> select() {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Provider> list=null;
		try {
			list=providerDao.select(connection, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override//查询总记录数
	public int selectCount() {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try {
			count=providerDao.selectCount(connection, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	@Override//分页查询供应商信息
	public List<Provider> select(int pageIndex, int pageSize) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Provider>list=null;
		try {
			list=providerDao.select(connection, ps, rs, pageIndex, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override//根据用户输入分页查询
	public List<Provider> selectForName(String name, Integer pageIndex,
			Integer pageSize) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Provider> list=null;
		try {
			list=providerDao.select(connection, ps, rs, name, pageIndex, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override//根据用户输入查询总记录数
	public int selectForNameCount(String name) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try {
			count=providerDao.selectCount(connection, ps, rs, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
