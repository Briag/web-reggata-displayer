package server.resource;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import server.Base;
import server.model.Boat;

public class BoatListResource extends ServerResource {
	
	@Get
	public Representation list() {
		
		EntityManager em = Base.getBase().getEntityManager();

		//Query
		Query query = em.createQuery(
				"select r from Boat r");
				
		List<Boat> results = query.getResultList();

	    return new JacksonRepresentation<List<Boat>>(results);
	}
	
	 @Post("json")
	 public void create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<Boat> jsonRepresentation = new JacksonRepresentation<Boat>(representation, Boat.class);
        Boat boat = jsonRepresentation.getObject();
	
		EntityManager em = Base.getBase().getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(boat);	
		
	    tx.commit();
	}	
}
