package test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	
	@Test
	public void tests(){
		ApplicationContext atc=new ClassPathXmlApplicationContext("beans.xml");
		Date date=(Date) atc.getBean("date");
		System.out.println(date);
	}
}
