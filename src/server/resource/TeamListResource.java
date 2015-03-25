package server.resource;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import server.Base;
import server.model.Team;
import server.model.Teammate;

public class TeamListResource extends ServerResource{
	
	@Get
	public Representation list() {
		
		Session s = Base.getSession();

		//Query
		Query query = s.createQuery(
				"select t from Team t");
				
		List<Team> results = query.list();
		s.close();

	    return new JacksonRepresentation<List<Team>>(results);
	}
	
	 @Post("json")
	 public void create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<Team> jsonRepresentation = new JacksonRepresentation<Team>(representation, Team.class);
        Team team = jsonRepresentation.getObject();
        team.setTeammates(new HashSet<Teammate>());
	
        Session s = Base.getSession();
		Transaction t = s.beginTransaction();
		
		s.persist(team);	        
	    t.commit();
		s.close();
	}	

}
