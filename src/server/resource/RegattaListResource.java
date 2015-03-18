package server.resource;

import java.io.IOException;
import java.util.HashSet;
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
import server.model.Regatta;
import server.model.Team;

public class RegattaListResource extends ServerResource {
	
	@Get
	public Representation list() {
		
		EntityManager em = Base.getBase().getEntityManager();

		// création d'une salle
		
		//Query
		Query query = em.createQuery(
				"select r from Regatta r");
				
		List<Regatta> results = query.getResultList();

	    return new JacksonRepresentation<List<Regatta>>(results);
	}
	
	 @Post("json")
	 public void create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<Regatta> jsonRepresentation = new JacksonRepresentation<Regatta>(representation, Regatta.class);
        Regatta regatta = jsonRepresentation.getObject();
        regatta.setTeam(new HashSet<Team>());
	
		EntityManager em = Base.getBase().getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(regatta);	        
	    tx.commit();
	}	
}
