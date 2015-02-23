package server.resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import server.Base;
import server.model.Team;

public class TeamResource extends ServerResource{

    private Long id;

	@Get
	public Representation get() {
		
		
		Map<String, Object> attributes = getRequest().getAttributes();
		
		EntityManager em = Base.getBase().getEntityManager();


		
		//Query
		Query query = em.createQuery(
				"select t from Team t where id = :id")
				.setParameter("id", Integer.valueOf((String) attributes.get("idTeam")));
		
		System.out.println(attributes.toString());
		
		List results = query.getResultList();
		Team team = null;
		if(!results.isEmpty()){
		    // ignores multiple results
		    team = (Team) results.get(0);
		}

		return new JacksonRepresentation<Team>(team);
	}
	
	 @Put("json")
	    public void update(Representation representation) throws IOException {
		 	
	        JacksonRepresentation<Team> jsonRepresentation = new JacksonRepresentation<Team>(representation, Team.class);
	        Team team = jsonRepresentation.getObject();
	        

			EntityManager em = Base.getBase().getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			em.merge(team);	        
	        tx.commit();
	    }
	 
	    @Delete
	    public void remove() {
			
			
			Map<String, Object> attributes = getRequest().getAttributes();
			
			EntityManager em = Base.getBase().getEntityManager();

			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
	    	Query query = em.createQuery(
					"delete Team where id = :id")
					.setParameter("id", Integer.valueOf((String) attributes.get("idTeam")));
			
	    	System.out.println(Integer.valueOf((String) attributes.get("idTeam")));
	    	
	    	query.executeUpdate();
	    	
	        tx.commit();
	    }
	
}

