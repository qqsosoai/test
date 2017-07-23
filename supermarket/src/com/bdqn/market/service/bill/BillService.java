package com.bdqn.market.service.bill;

import java.util.List;

import com.bdqn.market.bean.Bill;
import com.bdqn.market.service.SuperService;

public interface BillService extends SuperService<Bill> {
	/**
	 * 根据供应商ID与是否付款查询账单	
	 * @param proId
	 * @param isPayment
	 * @return
	 */
	List<Bill> selectProName(Integer proId,Integer isPayment,Integer pageIndex,Integer pageSize);
	/**
	 * 多条件分页查询
	 * @param BillName
	 * @param proId
	 * @param isPayment
	 * @return
	 */
	List<Bill> selectForName(String BillName,Integer proId,Integer isPayment,Integer pageIndex,Integer pageSize);
	/**
	 * 分页多条件查询
	 * @param BillName
	 * @param proId
	 * @param isPayment
	 * @return
	 */
	int selectForNameCount(String BillName,Integer proId,Integer isPayment);
	
}
