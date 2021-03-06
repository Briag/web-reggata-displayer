package server.resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import server.Base;
import server.model.Team;
import server.model.Teammate;

public class TeammateResource extends ServerResource{

    private Long id;

	@Get
	public Representation get() {
		
		
		Map<String, Object> attributes = getRequest().getAttributes();

		Session s = Base.getSession();
		
		//Query
		Query query = s.createQuery(
				"select t from Teammate t where id = :id")
				.setParameter("id", Integer.valueOf((String) attributes.get("idTeammate")));
		
		System.out.println(attributes.toString());
		
		List results = query.list();
		Teammate teammate = null;
		if(!results.isEmpty()){
		    // ignores multiple results
		    teammate = (Teammate) results.get(0);
		}
		s.close();
		
		return new JacksonRepresentation<Teammate>(teammate);
	}
	
	 @Put("json")
	    public void update(Representation representation) throws IOException {
		 	
	        JacksonRepresentation<Teammate> jsonRepresentation = new JacksonRepresentation<Teammate>(representation, Teammate.class);
	        Teammate teammate = jsonRepresentation.getObject();
	        


			Session s = Base.getSession();
			Transaction t = s.beginTransaction();
			
			s.merge(teammate);	   
    
	        t.commit();
			s.close();
	    }
	 
	    @Delete
	    public void remove() {
			
			
			Map<String, Object> attributes = getRequest().getAttributes();
			

			Session s = Base.getSession();
			Transaction t = s.beginTransaction();
			
	    	Query query = s.createQuery(
					"delete Teammate where id = :id")
					.setParameter("id", Integer.valueOf((String) attributes.get("idTeammate")));
			
	    	System.out.println(Integer.valueOf((String) attributes.get("idTeammate")));
	    	
	    	query.executeUpdate();
	    	
	        t.commit();
			s.close();
	        
	    }
	
}

