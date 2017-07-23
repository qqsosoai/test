package com.bdqn.market.dao.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bdqn.market.bean.Bill;
import com.bdqn.market.dao.SuperDao;

public interface BillDao extends SuperDao<Bill> {
	/**
	 * 账单的分页查询
	 * @param pageIndex当前页码
	 * @param pageSize页大小
	 * @return账单集合
	 */
	List<Bill> select(Connection connection,PreparedStatement ps,ResultSet rs,int pageIndex,int pageSize)throws SQLException;
	/**
	 * 根据供应商名称查询账单分页
	 * @param connection数据库连接对象
	 * @param ps执行语句对象
	 * @param rs结果集对象
	 * @param providerName供应商名称
	 * @param pageIndex当前页码
	 * @param pageSize页大小
	 * @return账单集合
	 * @throws SQLException
	 */
	List<Bill> selectIs(Connection connection,PreparedStatement ps,ResultSet rs,String providerName,int pageIndex,int pageSize)throws SQLException;
	/**
	 * 根据是否付款查询账单分页
	 * @param connection数据库连接对象
	 * @param ps执行语句对象
	 * @param rs结果集对象
	 * @param isPayment是否付款
	 * @param pageIndex当前页码
	 * @param pageSize页大小
	 * @return
	 * @throws SQLException
	 */
	List<Bill> selectIs(Connection connection,PreparedStatement ps,ResultSet rs,Integer isPayment,int pageIndex,int pageSize)throws SQLException;
	
	/**
	 * 根据供应商名称删除
	 * @param providerName供应商名称
	 * @return影响行数
	 */
	int delete(Connection connection,PreparedStatement ps,String providerName)throws SQLException;
	/**
	 * 根据供应商名称或是否付款查询账单
	 * @param connection数据库连接
	 * @param ps语句执行对象
	 * @param rs结果集对象
	 * @param proId供应商名称
	 * @param isPayment是否付款
	 * @return 返回账单集合
	 * @throws SQLException
	 */
	List<Bill> select(Connection connection,PreparedStatement ps,ResultSet rs,Integer proId,Integer isPayment,Integer pageIndex,Integer pageSize)throws SQLException;
	/**
	 * 根据供应商ID查询该供应商账单总记录数
	 * @param connection
	 * @param ps
	 * @param rs
	 * @param proId
	 * @return
	 * @throws SQLException
	 */
	int selectIsPro(Connection connection,PreparedStatement ps,ResultSet rs,int proId)throws SQLException;
	
	/**
	 * 多条件查询
	 * @param connection数据库连接对象
	 * @param ps语句发送对象
	 * @param rs结果集对象
	 * @param billName用户输入商品名称
	 * @param proId供应商ID
	 * @param isPay是否付款
	 * @return集合
	 */
	List<Bill> select(Connection connection,PreparedStatement ps,ResultSet rs,String billName,Integer proId,Integer isPay,Integer pageIndex,Integer pageSize)throws SQLException;
	
	/**
	 * 多条件查询出总页数
	 * @param connection数据库连接对象
	 * @param ps语句发送对象
	 * @param rs结果集对象
	 * @param billName用户输入商品名称
	 * @param proId供应商ID
	 * @param isPay是否付款
	 * @return 总记录数
	 * @throws SQLException
	 */
	int selectCount(Connection connection,PreparedStatement ps,ResultSet rs,String billName,Integer proId,Integer isPay)throws SQLException;
	/**
	 * 更改账单供应商名称
	 * @param connection数据库连接对象
	 * @param ps语句发送对象
	 * @param proName原供应商名称
	 * @param change改变的供应商名称
	 * @return影响行数
	 * @throws SQLException
	 */
	int update(Connection connection,PreparedStatement ps,String proName,String change)throws SQLException;
	/**
	 * 根据供应商名称查询账单总数
	 * @param connection数据库连接对象
	 * @param ps语句发送对象
	 * @param rs结果集对象
	 * @param proName供应商名
	 * @return返回该帐单的总记录数
	 * @throws SQLException
	 */
	int selectProNameCount(Connection connection,PreparedStatement ps,ResultSet rs,String proName)throws SQLException;
}
