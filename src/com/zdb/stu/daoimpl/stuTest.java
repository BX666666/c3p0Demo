package com.zdb.stu.daoimpl;

import org.junit.Test;
import com.zdb.stu.domain.student;
import cn.itcast.commons.CommonUtils;;

public class stuTest {
	@Test
	public void fun1() {
		TeaDaoimpl dao = new TeaDaoimpl();
		for(int i = 1; i <= 30; i++) {
			student s = new student();
			
			s.setStuId("0"+i);
			s.setStuName("学生_" + i);
			s.setStuSex(i%2==0?"�?":"�?");
			s.setStuAge(i%2==0?"18":"20");
			s.setStuNum("201717" + i);
			s.setStuScore(i%2==0?"98":"100");
			
			dao.add(s);
		}
	}
}
