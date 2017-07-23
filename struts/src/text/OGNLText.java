package text;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

import org.junit.Test;

import cn.bdqn.pojo.User;

public class OGNLText {

	@Test
	public void test() {
		User user1=new User("aaa1", "aaa1");
		User user2=new User("aaa2", "aaa2");
		User user3=new User("aaa3", "aaa3");
		Map<String , Object> map=new HashMap<String, Object>();
		map.put("u1", user1);
		map.put("u2", user2);
		map.put("u3", user3);
		try {
			System.out.println(Ognl.getValue("#context.u3.password",map, user1));
		} catch (OgnlException e) {
			e.printStackTrace();
		}
	}

}
