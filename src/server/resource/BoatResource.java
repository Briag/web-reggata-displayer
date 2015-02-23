package server.resource;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import server.Base;
import server.model.Boat;

public class BoatResource extends ServerResource {

    private Long id;

	@Get
	public Representation get() {
		
		
		Map<String, Object> attributes = getRequest().getAttributes();
		
		EntityManager em = Base.getBase().getEntityManager();

		
		//Query
		Query query = em.createQuery(
				"select r from Boat r where id = :id")
				.setParameter("id", Integer.valueOf((String) attributes.get("idBoat")));
		
		System.out.println(attributes.toString());
		
		List results = query.getResultList();
		Boat boat = null;
		if(!results.isEmpty()){
		    // ignores multiple results
		    boat = (Boat) results.get(0);
		}

		return new JacksonRepresentation<Boat>(boat);
	}
	
	 @Put("json")
	    public void update(Representation representation) throws IOException {
		 	
	        JacksonRepresentation<Boat> jsonRepresentation = new JacksonRepresentation<Boat>(representation, Boat.class);
	        Boat regatta = jsonRepresentation.getObject();
	        

			EntityManager em = Base.getBase().getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			em.merge(regatta);	        
	        tx.commit();
	    }
	 
	    @Delete
	    public void remove() {
			
			
			Map<String, Object> attributes = getRequest().getAttributes();
			
			EntityManager em = Base.getBase().getEntityManager();

			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
	    	Query query = em.createQuery(
					"delete Boat where id = :id")
					.setParameter("id", Integer.valueOf((String) attributes.get("idBoat")));
			
	    	query.executeUpdate();
	    	
	        tx.commit();
	    }
	
}
