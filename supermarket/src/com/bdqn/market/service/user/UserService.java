package com.bdqn.market.service.user;

import com.bdqn.market.bean.User;
import com.bdqn.market.service.SuperService;

public interface UserService extends SuperService<User> {
	/**
	 * 修改用户密码
	 * @param password新密码
	 * @return 是否修改成功
	 */
	boolean update(int id,String password);
	/**
	 * 根据用户名查询user对象
	 * @param name用户名
	 * @return user对象
	 */
	User login(String name);
	/**
	 * 根据用户输入查询总记录数
	 * @return
	 */
	int selectCount(String name);
	/**
	 * 判断改登录用户能否删除用户
	 * @param loginId登录用户ID
	 * @param delId删除用户ID
	 * @return
	 */
	int delete(Integer loginId,Integer delId);
}
