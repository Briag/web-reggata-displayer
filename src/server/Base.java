package server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class Base {

	private static SessionFactory sessionFactory; 
	
	@SuppressWarnings("deprecation")
	public static void open() { 
		try { 
			sessionFactory = new AnnotationConfiguration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
			} 
		catch (Throwable ex) { throw new ExceptionInInitializerError(ex); 
		} 
	} 
	
	public static SessionFactory getSessionFactory() { return sessionFactory; }
	
	
	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
