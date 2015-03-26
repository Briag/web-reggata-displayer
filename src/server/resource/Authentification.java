package server.resource;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import server.Base;
import server.model.Boat;
import server.model.User;

public class Authentification extends ServerResource  {

	static private SecureRandom random = new SecureRandom();
	static private ArrayList<String> authentificatedUser = new ArrayList<String>();
	
	static private String generateSessionToken(int id) {
		String token = new BigInteger(130, random).toString(32);
		authentificatedUser.add(token);
		return token;
	}

	/*
	 * Create or delete user connection
	 */
	 @Post("json")
	 public JacksonRepresentation<User> create(Representation representation) throws IOException {
		 	
	    JacksonRepresentation<User> jsonRepresentation = new JacksonRepresentation<User>(representation, User.class);
	    User user = jsonRepresentation.getObject();

	    if(user.getToken() == null) {
		    Session s = Base.getSession();
			
			Query query = s.createQuery(
					"select u from User u where username = :username and password = :password ");
			
			query.setParameter("username", user.getUsername()).setParameter("password", user.getPassword());
			
			List<User> result = query.list();
			
			System.out.println(result.size());
			if(result.size() == 0)
				throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
			
			User u = result.get(0);
			u.setToken(generateSessionToken(u.getIdUser()));
			s.close();
			
			return new JacksonRepresentation<User>(result.get(0));
	    }
	    else {
	    	authentificatedUser.remove(user.getToken());
	    	return new JacksonRepresentation<User>(null);
	    }
				
	}	
}
