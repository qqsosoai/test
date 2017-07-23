package com.bdqn.market.service;

import java.util.List;

public interface SuperService<T> {
	/**
	 * 根据用户输入查询集合
	 * @param name用户输入名称
	 * @return 返回泛型集合
	 */
	List<T> selectForName(String name,Integer pageIndex,Integer pageSize);
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return 返回泛型对象
	 */
	T select(int id);
	/**
	 * 查询所有数据
	 * @return集合
	 */
	List<T> select();
	/**
	 * 查询数据总记录数
	 * @return
	 */
	int selectCount();
	/**
	 * 分页查询数据
	 * @param pageIndex当前页码
	 * @param pageSize页大小
	 * @return返回集合
	 */
	List<T> select(int pageIndex,int pageSize);
	/**
	 * 新增数据
	 * @param t实体类
	 * @return 新增是否成功
	 */
	boolean insert(T t);
	/**
	 * 根据ID删除数据
	 * @param id
	 * @return 影响行数
	 */
	boolean delete(int id);
	/**
	 * 修改数据
	 * @param t实体类
	 * @return 影响行数
	 */
	boolean update(T t);
	
	
}
