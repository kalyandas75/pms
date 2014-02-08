package pms.logic;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class LoginServiceTest {

	@Test
	public void testValidateUser() {
		//fail("Not yet implemented");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pms");
		EntityManager em = emf.createEntityManager();
		LoginService ls = new LoginService();
		ls.setEntityManager(em);
		try {
			ls.validateUser("dummy.doctor","local123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

}
