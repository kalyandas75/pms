package pms.logic;

import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import pms.listeners.EMF;

public class Service {
	protected EntityManager em;
	protected ResourceBundle bundle = ResourceBundle.getBundle("Messages");
	
	protected Service(){
		createEntityManager();
	}
	protected Service(EntityManager em){
		this.em = em;
	}

	protected void setEntityManager(EntityManager em) {
		this.em = em;
	}

	protected void createEntityManager() {
		if (em == null) {
			em = EMF.createEntityManager();
		}
	}

	protected void closeEM() {
		if (em != null) {
			em.close();
		}
	}
}
