package com.bdqn.market.dao.bill.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bdqn.market.bean.Bill;
import com.bdqn.market.dao.BaseDao;

public class BillDaoImplTest {
	private BillDaoImpl dao;

	@Before
	public void setUp() throws Exception {
		dao=new BillDaoImpl();
	}

	@Test
	public void test() {
		Connection connection=BaseDao.getConnection();
		String condition="可";
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Bill>list=new ArrayList<Bill>();
		try {
			list=dao.select(connection, ps, rs, condition);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
