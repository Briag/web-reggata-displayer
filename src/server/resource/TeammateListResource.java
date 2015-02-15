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
import server.model.Teammate;;

public class TeammateListResource extends ServerResource{
	
	@Get
	public Representation list() {
		
		EntityManager em = Base.getBase().getEntityManager();

		//Query
		Query query = em.createQuery(
				"select t from Teammate t");
				
		List<Teammate> results = query.getResultList();

	    return new JacksonRepresentation<List<Teammate>>(results);
	}
	
	 @Post("json")
	 public void create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<Teammate> jsonRepresentation = new JacksonRepresentation<Teammate>(representation, Teammate.class);
        Teammate teammate = jsonRepresentation.getObject();
	
        System.out.println(teammate.getFirstname());
		EntityManager em = Base.getBase().getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(teammate);	        
	    tx.commit();
	}	

}
