package server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Base {

	static Base base;
	
	EntityManagerFactory emf = null;
	EntityManager em = null;

	static public Base getBase() {
		if( base == null) {
			base = new Base();
			base.ouvrir();
		}
		
		return base;
			
	}
	
	static public void closeBase() {
		base.fermer();
		base = null;
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	private void ouvrir() {
		emf = Persistence.createEntityManagerFactory("tphibernate");
		em = emf.createEntityManager();
	}
	
	private void fermer() {
		em.close();
		emf.close();
	}

}
