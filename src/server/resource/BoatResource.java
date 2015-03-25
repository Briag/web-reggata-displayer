package server.resource;

import java.io.IOException;
import java.util.Date;
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
import server.model.Boat;

public class BoatResource extends ServerResource {

    private Long id;

	@Get
	public Representation get() {
		
		
		Map<String, Object> attributes = getRequest().getAttributes();
		
		Session s = Base.getSession();
		
		//Query
		Query query = s.createQuery(
				"select r from Boat r where id = :id")
				.setParameter("id", Integer.valueOf((String) attributes.get("idBoat")));
		
		System.out.println(attributes.toString());
		
		List results = query.list();
		Boat boat = null;
		if(!results.isEmpty()){
		    // ignores multiple results
		    boat = (Boat) results.get(0);
		}
		s.close();

		return new JacksonRepresentation<Boat>(boat);
	}
	
	 @Put("json")
	    public void update(Representation representation) throws IOException {
		 	
	        JacksonRepresentation<Boat> jsonRepresentation = new JacksonRepresentation<Boat>(representation, Boat.class);
	        Boat regatta = jsonRepresentation.getObject();
	        

	        Session s = Base.getSession();
			Transaction t = s.beginTransaction();
			
			s.merge(regatta);	        
	        t.commit();
			s.close();
	    }
	 
	    @Delete
	    public void remove() {
			
			
			Map<String, Object> attributes = getRequest().getAttributes();
			
			Session s = Base.getSession();
			Transaction t = s.beginTransaction();
			
	    	Query query = s.createQuery(
					"delete Boat where id = :id")
					.setParameter("id", Integer.valueOf((String) attributes.get("idBoat")));
			
	    	query.executeUpdate();
	    	
	        t.commit();
			s.close();
	    }
	
}
