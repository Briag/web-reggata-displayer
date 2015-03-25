package server.resource;

import java.io.IOException;
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
import server.model.Teammate;;


public class TeammateListResource extends ServerResource {

	
	@Get
	public Representation list() {
		
		Session s = Base.getSession();
		
		//Query
		Query query = s.createQuery(
				"select t from Teammate t");
				
		List<Teammate> results = query.list();
		s.close();

	    return new JacksonRepresentation<List<Teammate>>(results);
	}
	
	 @Post("json")
	 public void create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<Teammate> jsonRepresentation = new JacksonRepresentation<Teammate>(representation, Teammate.class);
        Teammate teammate = jsonRepresentation.getObject();
	        
        Session s = Base.getSession();
		Transaction t = s.beginTransaction();
		s.persist(teammate);	        
	    t.commit();
		s.close();
	}	

}
