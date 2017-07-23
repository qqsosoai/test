package cn.bcqn.text;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.bdqn.market.bean.Bill;
import com.bdqn.market.service.bill.BillService;
import com.bdqn.market.service.bill.impl.BillServiceImpl;
import com.bdqn.market.service.provider.ProviderServicec;
import com.bdqn.market.service.provider.impl.ProviderServiceImpl;
import com.bdqn.market.service.user.UserService;
import com.bdqn.market.service.user.impl.UserServiceImpl;

public class ServiceTest {
	BillService billService;
	ProviderServicec providerServicec;
	UserService userService;
	Logger log;
	@Before
	public void setUp() throws Exception {
		billService=new BillServiceImpl();
		providerServicec=new ProviderServiceImpl();
		userService=new UserServiceImpl();
		log=log.getLogger(ServiceTest.class);
	}

	@Test
	public void test() {
		List<Bill> list=billService.selectForName("\\");
		for (Bill bill : list) {
			System.out.println(bill);
		}
	}
	@Test
	public void testInsert() {
		Bill t=new Bill(0, "15", "第%三_号\\", "一火车", "伊利", new BigDecimal(11.0), new BigDecimal(30000.00), 0, 3, new Date(), 3, new Date());
		boolean flag=billService.insert(t);
		if (flag) {
			System.out.println("新增成功");
		}else {
			System.out.println("新增失败");
		}
	}
	@Test
	public void testId() {
		int id=3;
		Bill bill=billService.select(id);
		System.out.println(bill);
	}
	@Test
	public void testDelId() {
		int id=23;
		boolean flag=billService.delete(id);
		if (flag) {
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
	}
	@Test
	public void testUp() {
		Bill t=new Bill(22, "18", "第%三_号\\", "一火车", "伊利", new BigDecimal(11.0), new BigDecimal(30000.00), 0, 3, new Date(), 3, new Date());
		boolean flag=billService.update(t);
		if (flag) {
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
	}
	@Test
	public void testName() {
		int proId=2;
		int isPayment=0;
		List<Bill>list=billService.select(proId, isPayment);
		for (Bill bill : list) {
			System.out.println(bill);
		}
	}
	@Test
	public void selectForNameCountTest() {
		int count=billService.selectForNameCount(null, null, null);
		log.debug(count);
	}
	@Test
	public void selectForNameTest() {
		List<Bill>list=billService.selectForName("可", null, 0, 2, 1);
		for (Bill bill : list) {
			log.debug(bill);
		}
	}
	

}
