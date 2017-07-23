package com.bdqn.market.dao.provider.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bdqn.market.bean.Provider;
import com.bdqn.market.dao.BaseDao;
import com.bdqn.market.dao.provider.ProviderDao;
import java.sql.Timestamp;

public class ProviderDaoImpl implements ProviderDao {
	//已测试
	@Override//查询所有供应商
	public List<Provider> select(Connection connection, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		List<Provider>list=new ArrayList<Provider>();
		String sql="select * from smbms_provider order by id desc";
		rs=BaseDao.execute(connection, ps, rs, sql);
		while (rs.next()) {
			Provider provider=new Provider(rs.getInt("id"), rs.getString("proCode"), rs.getString("proName"), 
					rs.getString("proDesc"), rs.getString("proContact"), rs.getString("proPhone"), 
					rs.getString("proAddress"), rs.getString("proFax"), rs.getInt("createdBy"), rs.getTimestamp("creationDate"),
					rs.getTimestamp("modifyDate"), rs.getInt("modifyBy"));
			list.add(provider);
		}
		BaseDao.closeConnection(null, ps, rs);
		return list;
	}
	//已测试
	@Override//根据ID查询供应商
	public Provider select(Connection connection, PreparedStatement ps,
			ResultSet rs, int id) throws SQLException {
		String sql = "select * from smbms_provider where id=? order by id desc";
		Object[] params={id};
		Provider provider=null;
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		if (rs.next()) {
			provider=new Provider(rs.getInt("id"), rs.getString("proCode"), rs.getString("proName"), 
					rs.getString("proDesc"), rs.getString("proContact"), rs.getString("proPhone"), 
					rs.getString("proAddress"), rs.getString("proFax"), rs.getInt("createdBy"), rs.getTimestamp("creationDate"),
					rs.getTimestamp("modifyDate"), rs.getInt("modifyBy"));
		}
		return provider;
	}
	//已测试
	@Override//更新供应商
	public int update(Connection connection, PreparedStatement ps, Provider e)
			throws SQLException {
		String sql="update smbms_provider set proCode=?,proName=?,proDesc=?,proContact=?," +
				"proPhone=?,proAddress=?,proFax=?,modifyDate=?,modifyBy=? where id=?";
		Object[] params={e.getProCode(),e.getProName(),e.getProDesc(),e.getProContact(),
				e.getProPhone(),e.getProAddress(),e.getProFax(), new Timestamp(e.getModifyDate().getTime()),
				e.getModifyBy(),e.getId()};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	//已测试
	@Override//新增供应商
	public int insert(Connection connection, PreparedStatement ps,
			Provider e) throws SQLException {
		String sql="insert into smbms_provider(proCode,proName,proDesc,proContact,proPhone," +
				"proAddress,proFax,createdBy,creationDate)" +
				" values(?,?,?,?,?,?,?,?,?);";
		Object[] params={e.getProCode(),e.getProName(),e.getProDesc(),e.getProContact(),e.getProPhone()
				,e.getProAddress(),e.getProFax(),e.getCreatedBy(),new Timestamp(e.getCreationDate().getTime())};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	//已测试
	@Override//根据ID删除供应商
	public int delete(Connection connection, PreparedStatement ps, int id)
			throws SQLException {
		String sql="delete from smbms_provider where id=?";
		Object[] params={id};
		int updateRows=BaseDao.execute(connection, ps, sql, params);
		BaseDao.closeConnection(null, ps, null);
		return updateRows;
	}
	//已测试
	@Override//分页查询供应商信息
	public List<Provider> select(Connection connection, PreparedStatement ps,
			ResultSet rs, int pageIndex, int pageSize) throws SQLException {
		String sql = "select * from smbms_provider order by id desc limit ?,?";
		Object[] params={(pageIndex-1)*pageSize,pageSize};
		List<Provider>list=new ArrayList<Provider>();
		rs=BaseDao.execute(connection, ps, rs, sql, params);
		while (rs.next()) {
			Provider provider=new Provider(rs.getInt("id"), rs.getString("proCode"), rs.getString("proName"), 
					rs.getString("proDesc"), rs.getString("proContact"), rs.getString("proPhone"), 
					rs.getString("proAddress"), rs.getString("proFax"), rs.getInt("createdBy"), rs.getTimestamp("creationDate"),
					rs.getTimestamp("modifyDate"), rs.getInt("modifyBy"));
			list.add(provider);
		}
		BaseDao.closeConnection(null, ps, rs);
		return list;
	}
	@Override//查询数据库记录数
	public int selectCount(Connection connection, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		String sql="select count(1) from smbms_provider";
		int count=0;
		rs=BaseDao.execute(connection, ps, rs, sql);
		if (rs.next()) {
			count=rs.getInt(1);
		}
		return count;
	}
	@Override//根据用户输入并分页查询
	public List<Provider> select(Connection connection, PreparedStatement ps,
			ResultSet rs, String condition, Integer pageIndex, Integer pageSize)
			throws SQLException {
		StringBuffer sql=new StringBuffer("select * from smbms_provider ");
		List<Object> params=new ArrayList<>();
		List<Provider> list=new ArrayList<Provider>();
		if (condition!=null && condition!="") {
			sql.append("WHERE proName LIKE ? ");
			if (condition.indexOf("%")>-1  || condition.indexOf("_")>-1) {
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
			Provider provider=new Provider(rs.getInt("id"), rs.getString("proCode"), rs.getString("proName"), 
					rs.getString("proDesc"), rs.getString("proContact"), rs.getString("proPhone"), 
					rs.getString("proAddress"), rs.getString("proFax"), rs.getInt("createdBy"), rs.getTimestamp("creationDate"),
					rs.getTimestamp("modifyDate"), rs.getInt("modifyBy"));
			list.add(provider);
		}
		return list;
	}
	@Override//根据用户输入查询总记录数
	public int selectCount(Connection connection, PreparedStatement ps,
			ResultSet rs, String name) throws SQLException {
		StringBuffer sql=new StringBuffer("select count(1) from smbms_provider ");
		List<Object> params=new ArrayList<>();
		int count=0;
		if (name!=null && name!="") {
			sql.append("WHERE proName LIKE ? ");
			if (name.indexOf("%")>-1  || name.indexOf("_")>-1) {
				name="\\"+name;
			}
			params.add("%"+name+"%");
		}
		rs=BaseDao.execute(connection, ps, rs, sql.toString(), params.toArray());
		while (rs.next()) {
			count=rs.getInt(1);
		}
		return count;
	}


}
