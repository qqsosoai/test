package com.bdqn.market.service.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bdqn.market.bean.Provider;
import com.bdqn.market.service.SuperService;

public interface ProviderServicec extends SuperService<Provider> {
	/**
	 * 根据用户输入查询总记录数
	 * @param name用户输入
	 * @return总记录数
	 */
	int selectForNameCount(String name);
}
