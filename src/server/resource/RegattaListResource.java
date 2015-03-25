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
import server.model.Regatta;
import server.model.Team;

public class RegattaListResource extends ServerResource {
	
	@Get
	public Representation list() {
		
		Session s = Base.getSession();
		//Query
		Query query = s.createQuery(
				"select r from Regatta r");
				
		List<Regatta> results = query.list();
		s.close();

	    return new JacksonRepresentation<List<Regatta>>(results);
	}
	
	 @Post("json")
	 public void create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<Regatta> jsonRepresentation = new JacksonRepresentation<Regatta>(representation, Regatta.class);
        Regatta regatta = jsonRepresentation.getObject();
        regatta.setTeam(new HashSet<Team>());
	
        Session s = Base.getSession();
		Transaction t = s.beginTransaction();
		
		s.persist(regatta);	        
	    t.commit();
		s.close();
	}	
}
