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
import server.model.Boat;

public class BoatListResource extends ServerResource {
	
	@Get
	public Representation list() {
		
		Session s = Base.getSession();
		//Query
		Query query = s.createQuery(
				"select r from Boat r");
				
		List<Boat> results = query.list();
		s.close();

	    return new JacksonRepresentation<List<Boat>>(results);
	}
	
	 @Post("json")
	 public void create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<Boat> jsonRepresentation = new JacksonRepresentation<Boat>(representation, Boat.class);
        Boat boat = jsonRepresentation.getObject();

        Session s = Base.getSession();
		Transaction t = s.beginTransaction();
		
		s.persist(boat);	
		
	    t.commit();
		s.close();
	}	
}
