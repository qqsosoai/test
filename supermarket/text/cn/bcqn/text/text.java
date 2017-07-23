package cn.bcqn.text;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.bdqn.market.bean.Bill;
import com.bdqn.market.bean.Provider;
import com.bdqn.market.bean.User;
import com.bdqn.market.dao.BaseDao;
import com.bdqn.market.dao.bill.BillDao;
import com.bdqn.market.dao.bill.impl.BillDaoImpl;
import com.bdqn.market.dao.provider.ProviderDao;
import com.bdqn.market.dao.provider.impl.ProviderDaoImpl;
import com.bdqn.market.dao.user.UserDao;
import com.bdqn.market.dao.user.impl.UserDaoImpl;

public class text {
	 //private static BillDao dao=new BillDaoImpl();
	 //private static ProviderDao dao=new ProviderDaoImpl();
	 private UserDao dao=new UserDaoImpl();
	 public static void main(String[] args) {
		Connection connection=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String condition="可";
		List<Bill> list=null;
		String providerName="菲力宾咖啡";
		int isPayment=0;
		Bill e=new Bill();
		e.setBillCode("14");
		e.setProductName("信心慢慢");
		e.setProductDesc("一卡车");
		e.setProductUnit("蒙牛");
		e.setProductCount(new BigDecimal(1.00));
		e.setTotalPrice(new BigDecimal(300.00));
		e.setIsPayment(1);
		e.setCreatedBy(2);
		e.setCreationDate(new Date());
		try {
			isPayment=dao.insert(connection, ps, rs, e);
		} catch (SQLException c) {
			c.printStackTrace();
		}
		if (isPayment>0) {
			System.out.println("删除成功");
		}
		/*for (Bill bill : list) {
			System.out.println(bill);
		}*/
	}
	 @Test
	 public void test(){
		 Connection connection=BaseDao.getConnection();
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 List<Provider>list=null;
		 Provider provider1=null;
		 String name="星巴克";
		 int id=8;
		 int is=0;
		 Provider e=new Provider(10, "8", "美年达", "美年达集团", "往先生", "13232167942", "中国", "0800-123456", 2, new Date(), new Date(), 2);
		 try {
			is=dao.delete(connection, ps, 10);
		} catch (SQLException c) {
			c.printStackTrace();
		}
		/* for (Provider provider : list) {
			System.out.println(provider);
		}*/
		//System.out.println(provider1);
		 if (is>0) {
			System.out.println("删除成功");
		}
		
	 }
	 @Test
	 public void testUser(){
		 Connection connection=BaseDao.getConnection();
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 List<User>list=null;
		 Date date = null;
		try {
			date = (new SimpleDateFormat("yyyy-MM-dd")).parse("1994-04-30");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		 User e=new User(6, "qq32145", "李娜", "lina123456", 1, date, "13273131046", "石家庄", 2, 2, new Date(), 2, new Date());
		 String userName="admin";
		 int is=0;
		 try {
			is=dao.update(connection, ps, 7, "123456");
		} catch (SQLException c) {
			c.printStackTrace();
		}
		/*for (User user : list) {
			System.out.println(user);
		}*/
		//System.out.println(e);
		if (is>0) {
			System.out.println("新增成功");
		}
	 }
}
