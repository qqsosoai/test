package com.bdqn.market.service.bill.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.bdqn.market.bean.Bill;
import com.bdqn.market.dao.BaseDao;
import com.bdqn.market.dao.bill.BillDao;
import com.bdqn.market.dao.bill.impl.BillDaoImpl;
import com.bdqn.market.dao.provider.ProviderDao;
import com.bdqn.market.dao.provider.impl.ProviderDaoImpl;
import com.bdqn.market.dao.user.UserDao;
import com.bdqn.market.dao.user.impl.UserDaoImpl;
import com.bdqn.market.service.bill.BillService;

public class BillServiceImpl implements BillService {
	private BillDao billDao;
	private ProviderDao providerDao;
	private UserDao userDao;
	public BillServiceImpl(){
		if (billDao==null) {
			billDao=new BillDaoImpl();
		}
		if (providerDao==null) {
			providerDao=new ProviderDaoImpl();
		}
		if (userDao==null) {
			userDao=new UserDaoImpl();
		}
	}
	//已测试
	@Override//根据ID查询具体账单
	public Bill select(int id) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Bill bill=null;
		try {
			bill=billDao.select(connection, ps, rs, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeConnection(connection, ps, rs);
		}
		return bill;
	}
	//已测试
	@Override//新增账单
	public boolean insert(Bill t) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);
			int updateRows=billDao.insert(connection,ps,t);
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
	//已测试
	@Override//根据ID删除账单
	public boolean delete(int id) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);
			int updateRows=billDao.delete(connection, ps, id);
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
		}finally{
			BaseDao.closeConnection(connection, ps, null);
		}
		return flag;
	}
	//已测试
	@Override//更新账单
	public boolean update(Bill t) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			connection.setAutoCommit(false);
			int updateRows=billDao.update(connection, ps, t);
			if (updateRows>0) {
				connection.commit();
				flag=true;
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			BaseDao.closeConnection(connection, ps, null);
		}
		return flag;
	}
	//已测试
	@Override//根据供应商名称与是否付款查询账单
	public List<Bill> selectProName(Integer proId, Integer isPayment,Integer pageIndex,Integer pageSize) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Bill> list=null;
		try {
			connection.setAutoCommit(false);
			if (isPayment>1) {
				isPayment=null;
			}
			list=billDao.select(connection, ps, rs, proId, isPayment,pageIndex,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			BaseDao.closeConnection(connection, ps, rs);
		}
		return list;
	}
	
	@Override//查询账单总记录数
	public int selectCount() {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try {
			count=billDao.selectCount(connection, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override//查询账单分页
	public List<Bill> select(int pageIndex, int pageSize) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Bill> list=null;
		try {
			list=billDao.select(connection, ps, rs, pageIndex, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override//查询账单所有信息
	public List<Bill> select() {
		Connection  connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Bill> list=null;
		try {
			list=billDao.select(connection, ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override//多条件分页查询
	public List<Bill> selectForName(String billName, Integer proId,
			Integer isPayment, Integer pageIndex, Integer pageSize) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Bill> list=null;
		try {
			list=billDao.select(connection, ps, rs, billName, proId, isPayment, pageIndex, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override//多条件查询总数
	public int selectForNameCount(String billName, Integer proId,
			Integer isPayment) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try {
			count=billDao.selectCount(connection, ps, rs, billName, proId, isPayment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	@Override//根据用户输入查询，废弃
	public List<Bill> selectForName(String name, Integer pageIndex,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
