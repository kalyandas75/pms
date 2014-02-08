package pms.logic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pms.exceptions.PMSException;
import pms.listeners.EMF;
import pms.model.Login;

public class LoginService {
	//@PersistenceContext(unitName = "pms")
	private EntityManager em; 
	ResourceBundle bundle = ResourceBundle.getBundle("Messages");
	private final static Logger LOGGER = Logger
			.getLogger(pms.logic.LoginService.class.getName());

	public Login validateUser(String userName, String password) throws PMSException {
		if(em==null)
		{
			em = EMF.createEntityManager();
		}
		LOGGER.info("User name:" + userName + ", Password:" + password);
		Login user = null;
		try {
			if (userName == null || userName.length() < 1) {
				LOGGER.log(Level.WARNING, bundle.getString("username.blank"));
				throw new PMSException(bundle.getString("username.blank"));
			}
			if (password == null || (password.length() < 1)) {
				LOGGER.log(Level.WARNING,
						bundle.getString("password.blank.error"),
						new Object[] { userName });
				throw new PMSException(bundle.getString("password.blank"));
			}
			
			user = em.find(Login.class, userName);

			if (user == null) {
				LOGGER.log(Level.WARNING,
						bundle.getString("user.notfound.error"),
						new Object[] { userName });
				throw new PMSException(bundle.getString("user.notfound"));
			}

			String dbPwd = user.getPassword();
			if (!password.equals(dbPwd)) {
				int invalidTryCount = user.getInvalidTryCount() + 1;
				user.setInvalidTryCount(invalidTryCount);
				LOGGER.log(Level.SEVERE,
						bundle.getString("password.mismatch.error"),
						new Object[] { userName, invalidTryCount });
				if (invalidTryCount > 3) {
					user.setIsLocked(true);
					user.setLockReason("Try Count Exceeded. Auto Locked.");
					user.setLockedBy("System");
					LOGGER.log(Level.SEVERE,
							bundle.getString("account.locked.error"),
							new Object[] { userName, invalidTryCount });
					throw new PMSException(bundle.getString("account.locked"));
				}
				throw new PMSException(bundle.getString("invalid.user"));
			}
			else{
				user.setInvalidTryCount(0);
				user.setLastLoggedOn(new Date());
				user.setLockReason(null);
				user.setLockedBy(null);
				user.setLockedOn(null);
			}
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(user);
			tx.commit();
		} finally {
			if(em!=null){
			em.close();
			}
		}
		return user;
	}

	public void changePassword(String userName, String newPassword) {

	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}
