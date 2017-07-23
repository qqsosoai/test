package com.bdqn.market.dao.bill.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bdqn.market.bean.Bill;
import com.bdqn.market.dao.BaseDao;
import com.bdqn.market.dao.SuperDao;
import com.bdqn.market.dao.bill.BillDao;

public class BillDaoImpl implements BillDao {
	//已测试
	@Override//查询所有账单
	public List<Bill> select(Connection connection, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		String sql="SELECT b.*,u.`userName` cname,u2.`userName` mname FROM smbms_bill b JOIN smbms_user u ON b.`createdBy`=u.`id` LEFT JOIN smbms_user u2 ON b.`modifyBy`=u2.`id` ORDER BY b.`id` DESC";
		List<Bill> list=new ArrayList<Bill>();
		rs=BaseDao.execute(connection, ps, rs, sql);
		while (rs.next()) {
			Bill bill=new Bill(rs.getInt("id"), rs.getString("billCode"), rs.getString("productName"), rs.getString("productDesc"), rs.getString("productUnit"), 
					rs.getBigDecimal("productCount"), rs.getBigDecimal("totalPrice"), rs.getInt("isPayment"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"), rs.getString("cname"), rs.getString("mname"));
			list.add(bill);
		}
		BaseDao.closeConnection(null, ps, rs);
		return list;
	}
	//已测试
	@Override//根据id查询账单
	public Bill select(Connection connection, PreparedStatement ps,
			ResultSet rs, int id) throws SQLException {
		String sql="SELECT b.*,u.`userName` cname,u2.`userName` mname FROM smbms_bill b JOIN smbms_user u ON b.`createdBy`=u.`id` LEFT JOIN smbms_user u2 ON b.`modifyBy`=u2.`id`WHERE b.`id`=?";
		Object[] params={id};
		Bill bill=null;
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		if (rs.next()) {
			bill=new Bill(rs.getInt("id"), rs.getString("billCode"), rs.getString("productName"), rs.getString("productDesc"), rs.getString("productUnit"), 
					rs.getBigDecimal("productCount"), rs.getBigDecimal("totalPrice"), rs.getInt("isPayment"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"), rs.getString("cname"), rs.getString("mname"));
		}
		BaseDao.closeConnection(null, ps, rs);
		return bill;
	}
	//已测试
	@Override//修改账单
	public int update(Connection connection, PreparedStatement ps, Bill e)
			throws SQLException {
		String sql="update smbms_bill set billCode=?,productName=?,productDesc=?," +
				"productUnit=?,productCount=?,totalPrice=?,isPayment=?,modifyBy=?,modifyDate=? where id=?";
		Object[] params={e.getBillCode(),e.getProductName(),e.getProductDesc(),e.getProductUnit(),e.getProductCount(),e.getTotalPrice(),
				e.getIsPayment(),e.getModifyBy(),new Timestamp(e.getModifyDate().getTime()),e.getId()};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	//已测试
	@Override//新增账单
	public int insert(Connection connection, PreparedStatement ps,
			 Bill e) throws SQLException {
		String sql="INSERT INTO smbms_bill(billCode,productName,productDesc," +
				"productUnit,productCount,totalPrice,isPayment,createdBy,creationDate) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] params={e.getBillCode(),e.getProductName(),e.getProductDesc(),e.getProductUnit(),
				e.getProductCount(),e.getTotalPrice(),e.getIsPayment(),e.getCreatedBy(),new Timestamp(e.getCreationDate().getTime())};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}

	//已测试
	@Override//账单的分页查询
	public List<Bill> select(Connection connection, PreparedStatement ps,
			ResultSet rs, int pageIndex, int pageSize) throws SQLException {
		String sql="SELECT b.*,u.`userName` cname,u2.`userName` mname FROM smbms_bill b JOIN smbms_user u ON b.`createdBy`=u.`id` LEFT JOIN smbms_user u2 ON b.`modifyBy`=u2.`id` ORDER BY b.`id` DESC limit ?,?";
		Object[] params={(pageIndex-1)*pageSize,pageSize};
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		List<Bill> list=new ArrayList<Bill>();
		while (rs.next()) {
			Bill bill=new Bill(rs.getInt("id"), rs.getString("billCode"), rs.getString("productName"), rs.getString("productDesc"), rs.getString("productUnit"), 
					rs.getBigDecimal("productCount"), rs.getBigDecimal("totalPrice"), rs.getInt("isPayment"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"), rs.getString("cname"), rs.getString("mname"));
			list.add(bill);
		}
		BaseDao.closeConnection(null, ps, rs);
		return list;
	}
	//已测试
	@Override//根据供应商查询
	public List<Bill> selectIs(Connection connection, PreparedStatement ps,
			ResultSet rs, String providerName,int pageIndex,int pageSize) throws SQLException {
		List<Bill>list=new ArrayList<Bill>();
		String sql="SELECT b.*,u.`userName` cname,u2.`userName` mname FROM smbms_bill b JOIN smbms_user u ON b.`createdBy`=u.`id` LEFT JOIN smbms_user u2 ON b.`modifyBy`=u2.`id` where b.`productUnit`=? ORDER BY b.`id` DESC limit ?,?";
		Object[] params={providerName,(pageIndex-1)*pageSize,pageSize};
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		while (rs.next()) {
			Bill bill=new Bill(rs.getInt("id"), rs.getString("billCode"), rs.getString("productName"), rs.getString("productDesc"), rs.getString("productUnit"), 
					rs.getBigDecimal("productCount"), rs.getBigDecimal("totalPrice"), rs.getInt("isPayment"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"), rs.getString("cname"), rs.getString("mname"));
			list.add(bill);
		}
		BaseDao.closeConnection(null, ps, rs);
		return list;
	}
	//已测试
	@Override//根据是否付款查询账单分页
	public List<Bill> selectIs(Connection connection, PreparedStatement ps,
			ResultSet rs, Integer isPayment,int pageIndex,int pageSize) throws SQLException {
		String sql="SELECT b.*,u.`userName` cname,u2.`userName` mname FROM smbms_bill b JOIN smbms_user u ON b.`createdBy`=u.`id` LEFT JOIN smbms_user u2 ON b.`modifyBy`=u2.`id` WHERE b.`isPayment`=? ORDER BY b.`id` DESC LIMIT ?,?";
		Object[] params={isPayment,(pageIndex-1)*pageSize,pageSize};
		List<Bill>list=new ArrayList<Bill>();
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		while (rs.next()) {
			Bill bill=new Bill(rs.getInt("id"), rs.getString("billCode"), rs.getString("productName"), rs.getString("productDesc"), rs.getString("productUnit"), 
					rs.getBigDecimal("productCount"), rs.getBigDecimal("totalPrice"), rs.getInt("isPayment"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"), rs.getString("cname"), rs.getString("mname"));
			list.add(bill);
		}
		BaseDao.closeConnection(null, ps, rs);
		return list;
	}
	//已测试
	@Override//根据供应商名称删除账单
	public int delete(Connection connection, PreparedStatement ps,
			String providerName) throws SQLException {
		String sql="delete from smbms_bill where productUnit=?";
		Object[] params={providerName};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	//已测试
	@Override//根据ID删除账单
	public int delete(Connection connection, PreparedStatement ps, int id)
			throws SQLException {
		String sql="delete from smbms_bill where id=?";
		Object[] params={id};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	@Override//根据供应商名称，是否付款查询
	public List<Bill> select(Connection connection, PreparedStatement ps,
			ResultSet rs, Integer proId, Integer isPayment,Integer pageIndex,Integer pageSize) throws SQLException {
		String sql;
		Object[] params = null;
		List<Bill> list=new ArrayList<Bill>();
		if (proId!=0 && isPayment!=null) {//代表查询某个供应商账单是否付款
			sql="SELECT b.* FROM smbms_bill b JOIN smbms_provider p ON b.`productUnit`=p.`proName` WHERE p.`id`=? AND b.`isPayment`=? ORDER BY id DESC";
			params=new Object[2];
			params[0]=proId;
			params[1]=isPayment;
		}else if(proId==0 && isPayment!=null){//代表查询所有账单是否付款
			sql="SELECT b.* FROM smbms_bill b JOIN smbms_provider p ON b.`productUnit`=p.`proName` WHERE b.`isPayment`=? ORDER BY id DESC";
			params=new Object[1];
			params[0]=isPayment;
		}else if (proId==0 && isPayment==null){//代表查询所有数据并分页
			sql="SELECT b.* FROM smbms_bill b JOIN smbms_provider p ON b.`productUnit`=p.`proName` ORDER BY id DESC LIMIT ?,?";
			params=new Object[2];
			params[0]=(pageIndex-1)*pageSize;
			params[1]=pageSize;
		}else {//代表是否付款项没填，只查询某供应商
			sql="SELECT b.* FROM smbms_bill b JOIN smbms_provider p ON b.`productUnit`=p.`proName` WHERE p.`id`=?";
			params=new Object[1];
			params[0]=proId;
		}
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		while (rs.next()) {
			Bill bill=new Bill(rs.getInt("id"), rs.getString("billCode"), rs.getString("productName"), rs.getString("productDesc"), rs.getString("productUnit"), 
					rs.getBigDecimal("productCount"), rs.getBigDecimal("totalPrice"), rs.getInt("isPayment"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"));
			list.add(bill);
		}
		return list;
	}
	@Override//查询总记录数
	public int selectCount(Connection connection, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		String sql="select count(1) from smbms_bill;";
		ps=connection.prepareStatement(sql);
		rs=BaseDao.execute(connection, ps, rs, sql);
		int count=0;
		while (rs.next()) {
			count=rs.getInt(1);
		}
		return count;
	}
	@Override//根据供应商id查询账单总记录数
	public int selectIsPro(Connection connection, PreparedStatement ps,
			ResultSet rs, int proId) throws SQLException {
		String sql="SELECT COUNT(1) FROM smbms_bill b JOIN smbms_provider p ON b.`productUnit`=p.`proName` WHERE p.`id`=?";
		Object[] params={proId};
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		int count=0;
		if (rs.next()) {
			count=rs.getInt(1);
		}
		return count;
	}
	@Override//查询多条件的总记录数
	public int selectCount(Connection connection, PreparedStatement ps,
			ResultSet rs, String billName, Integer proId, Integer isPay)
			throws SQLException {
		int count=0;//记录条件查询的总记录数
		boolean flag=false;//判断第一个if块是否进入
		boolean isPayFlag=false;//判断第二个if块是否进入
		List<Object> params=new ArrayList<>();
		StringBuffer sb=new StringBuffer("SELECT COUNT(1) FROM smbms_bill b JOIN smbms_provider p ON b.`productUnit`=p.`proName` ");
		if (proId!=null && proId>0) {//判断用户是否
			sb.append(" WHERE p.`id`=? ");
			params.add(proId);
			flag=true;
		}
		if (isPay!=null && (isPay==0 || isPay==1)) {
			isPayFlag=true;
			if (flag) {//proId>0 if块进入拼接and
				sb.append(" AND b.`isPayment`=? ");
				params.add(isPay);
			}else {//proId>0 if块未进入不拼接and
				sb.append(" WHERE b.`isPayment`=? ");
				params.add(isPay);
			}
		}
		if (billName!=null && billName!="") {
			if (isPayFlag) {//第二个if代码块isPay运行
				sb.append(" AND b.`productName` LIKE ? ");
			}else {//第二个if代码块isPay没有运行
				sb.append(" WHERE b.`productName` LIKE ? ");
			}
			if (billName.indexOf("%")>-1  || billName.indexOf("_")>-1) {
				billName="\\"+billName;
			}
			params.add("%"+billName+"%");
		}
		rs=BaseDao.execute(connection, ps, rs, sb.toString(), params.toArray());
		if (rs.next()) {
			count=rs.getInt(1);
		}
		BaseDao.closeConnection(null, null, rs);
		return count;
	}
	@Override//分页查询查询条件的总记录数
	public List<Bill> select(Connection connection, PreparedStatement ps,
			ResultSet rs, String billName, Integer proId, Integer isPay,
			Integer pageIndex, Integer pageSize) throws SQLException {
		StringBuffer sb=new StringBuffer("SELECT * FROM smbms_bill b JOIN smbms_provider p ON b.`productUnit`=p.`proName` ");
		List<Bill> list=new ArrayList<>();
		List<Object> params=new ArrayList<>();
		boolean flag=true;//判断第一个if块是否进入
		if (proId!=null && proId>0) {//判断用户是否
			sb.append(" WHERE p.`id`=? ");
			params.add(proId);
			flag=false;
		}
		if (isPay!=null && (isPay==0 || isPay==1)) {
			if (flag) {//判断他是否是第一个拼接
				sb.append(" WHERE b.`isPayment`=? ");
				flag=false;
			}else {//不是第一个
				sb.append(" AND b.`isPayment`=? ");
			}
			params.add(isPay);
		}
		if (billName!=null && billName!="") {
			if (flag) {//判断他是否是第一个拼接
				sb.append(" WHERE b.`productName` LIKE ? ");
			}else {//不是第一个
				sb.append(" AND b.`productName` LIKE ? ");
			}
			if (billName.indexOf("%")>-1  || billName.indexOf("_")>-1) {
				billName="\\"+billName;
			}
			params.add("%"+billName+"%");
		}
		sb.append(" ORDER BY b.`id` DESC ");//数据降序排列
		if (pageIndex!=null &&pageSize!=null) {
			sb.append(" limit ?,? ");
			params.add((pageIndex-1)*pageSize);
			params.add(pageSize);
		}
		
		rs=BaseDao.execute(connection, ps, rs, sb.toString(), params.toArray());
		while (rs.next()) {
			Bill bill=new Bill(rs.getInt("id"), rs.getString("billCode"), rs.getString("productName"), rs.getString("productDesc"), rs.getString("productUnit"), 
					rs.getBigDecimal("productCount"), rs.getBigDecimal("totalPrice"), rs.getInt("isPayment"), rs.getInt("createdBy"), 
					rs.getTimestamp("creationDate"), rs.getInt("modifyBy"), rs.getTimestamp("modifyDate"));
			list.add(bill);
		}
		BaseDao.closeConnection(null, null, rs);
		return list;
	}
	@Override//根据供应商名称更改供应商
	public int update(Connection connection, PreparedStatement ps,
			String proName, String change) throws SQLException {
		String sql="update smbms_bill set productUnit=? where productUnit=?";
		Object[] params={change,proName};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		return updateRows;
	}
	@Override//根据供应商查询账单记录数
	public int selectProNameCount(Connection connection, PreparedStatement ps,
			ResultSet rs, String proName) throws SQLException {
		String sql="select count(1) from smbms_bill where productUnit=?";
		Object[] params={proName};
		int count=0;
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		while (rs.next()) {
			count=rs.getInt(1);
		}
		return count;
	}
	@Override//废弃方法
	public List<Bill> select(Connection connection, PreparedStatement ps,
			ResultSet rs, String condition, Integer pageIndex, Integer pageSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
