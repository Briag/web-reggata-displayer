package server.resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.hibernate.Query;

import server.Base;
import server.model.Team;

public class TeamResource extends ServerResource{

    private Long id;

	@Get
	public Representation get() {
		
		
		Map<String, Object> attributes = getRequest().getAttributes();
		
		Session s = Base.getSession();

		//Query
		Query query = s.createQuery(
				"select t from Team t where id = :id")
				.setParameter("id", Integer.valueOf((String) attributes.get("idTeam")));
		
		System.out.println(attributes.toString());
		
		List results = query.list();
		Team team = null;
		if(!results.isEmpty()){
		    // ignores multiple results
		    team = (Team) results.get(0);
		}

		s.close();
		return new JacksonRepresentation<Team>(team);
	}
	
	 @Put("json")
	    public void update(Representation representation) throws IOException {
		 	
	        JacksonRepresentation<Team> jsonRepresentation = new JacksonRepresentation<Team>(representation, Team.class);
	        Team team = jsonRepresentation.getObject();
	        
			Session s = Base.getSession();
			Transaction t = s.beginTransaction();
			
			s.merge(team); 
			
			t.commit();
			s.close();
	    }
	 
	    @Delete
	    public void remove() {
			
			
			Map<String, Object> attributes = getRequest().getAttributes();
			

			Session s = Base.getSession();
			Transaction t = s.beginTransaction();
			
	    	Query query = s.createQuery(
					"delete Team where id = :id")
					.setParameter("id", Integer.valueOf((String) attributes.get("idTeam")));
			
	    	System.out.println(Integer.valueOf((String) attributes.get("idTeam")));
	    	
	    	query.executeUpdate();
	    	
	        t.commit();
			s.close();
	    }
	
}

