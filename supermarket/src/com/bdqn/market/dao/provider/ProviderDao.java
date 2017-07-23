package com.bdqn.market.dao.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bdqn.market.bean.Provider;
import com.bdqn.market.dao.SuperDao;

public interface ProviderDao extends SuperDao<Provider>{
	/**
	 * 分页查询供应商信息
	 * @param pageIndex当前页码
	 * @param pageSize也页大小
	 * @return 供应商集合
	 */
	List<Provider> select(Connection connection,PreparedStatement ps,ResultSet rs,int pageIndex,int pageSize)throws SQLException;
	/**
	 * 根据用户输入查询总记录数
	 * @param connection数据库连接对象
	 * @param ps语句发送对象
	 * @param rs结果集对象
	 * @param name用户输入
	 * @return返回总记录数
	 * @throws SQLException
	 */
	int selectCount(Connection connection,PreparedStatement ps,ResultSet rs,String name)throws SQLException;
}
