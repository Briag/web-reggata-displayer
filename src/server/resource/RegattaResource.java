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
import server.model.Regatta;

public class RegattaResource extends ServerResource {

    private Long id;

	@Get
	public Representation get() {
		
		
		Map<String, Object> attributes = getRequest().getAttributes();
		
		Session s = Base.getSession();

		// création d'une salle
		
		//Query
		Query query = s.createQuery(
				"select r from Regatta r where id = :id")
				.setParameter("id", Integer.valueOf((String) attributes.get("idRegatta")));
		
		System.out.println(attributes.toString());
		
		List results = query.list();
		Regatta regatta = null;
		if(!results.isEmpty()){
		    // ignores multiple results
		    regatta = (Regatta) results.get(0);
		}
		s.close();

		return new JacksonRepresentation<Regatta>(regatta);
	}
	
	 @Put("json")
	    public void update(Representation representation) throws IOException {
		 	
	        JacksonRepresentation<Regatta> jsonRepresentation = new JacksonRepresentation<Regatta>(representation, Regatta.class);
	        Regatta regatta = jsonRepresentation.getObject();
	        
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
					"delete Regatta where id = :id")
					.setParameter("id", Integer.valueOf((String) attributes.get("idRegatta")));
			
	    	System.out.println(Integer.valueOf((String) attributes.get("idRegatta")));
	    	
	    	query.executeUpdate();
	    	
	        t.commit();
			s.close();
	    }
	
}
