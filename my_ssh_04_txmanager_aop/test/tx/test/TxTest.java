package tx.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ldu.third.model.User;
import com.ldu.third.service.LoginManager;

public class TxTest {
	@Test
	public void txtt(){
		ApplicationContext atc=new ClassPathXmlApplicationContext("beans.xml");
		LoginManager lm=(LoginManager) atc.getBean("loginManager");
		User user=new User();
		user.setPassWord("ee");
		user.setUserName("dd");
		
		//lm.add(user);
		//lm.delete(15);
		lm.deladdTest();
	}
	
}
