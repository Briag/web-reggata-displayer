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
import server.model.Team;
import server.model.Teammate;

public class TeamListResource extends ServerResource{
	
	@Get
	public Representation list() {
		
		EntityManager em = Base.getBase().getEntityManager();

		//Query
		Query query = em.createQuery(
				"select t from Team t");
				
		List<Team> results = query.getResultList();

	    return new JacksonRepresentation<List<Team>>(results);
	}
	
	 @Post("json")
	 public void create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<Team> jsonRepresentation = new JacksonRepresentation<Team>(representation, Team.class);
        Team team = jsonRepresentation.getObject();
        team.setTeammates(new HashSet<Teammate>());
	
		EntityManager em = Base.getBase().getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(team);	        
	    tx.commit();
	}	

}
