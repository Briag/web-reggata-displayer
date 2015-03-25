package server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import server.resource.BoatListResource;
import server.resource.BoatResource;
import server.resource.RegattaListResource;
import server.resource.RegattaResource;
import server.resource.TeamListResource;
import server.resource.TeamResource;
import server.resource.TeammateListResource;
import server.resource.TeammateResource;
import server.resource.Authentification;

public class RegattaApplication extends Application {

    public RegattaApplication() {
        setName("Web Regatta");
        setDescription("RESTlet + AngularJS integration");
        setOwner("Le Nabec Briag / Alexandre Hoyet");
        setAuthor("Le Nabec Briag / Alexandre Hoyet");
    }

    @Override
    public Restlet createInboundRoot() {
        Directory directory = new Directory(getContext(), "clap://class/resources/static/");
        directory.setDeeplyAccessible(true);

        System.out.println(directory.getRootRef().getPath());
        Router router = new Router(getContext());
        // The AngluarJS direcory
        router.attach("/web", directory);
        
        router.attach("/rest/regatta/{idRegatta}",RegattaResource.class);
        router.attach("/rest/regatta",RegattaListResource.class);
                
        router.attach("/rest/teammate/{idTeammate}",TeammateResource.class);
        router.attach("/rest/teammate",TeammateListResource.class);
        
        router.attach("/rest/boat/{idBoat}",BoatResource.class);
        router.attach("/rest/boat",BoatListResource.class);
        
        router.attach("/rest/team/{idTeam}",TeamResource.class);
        router.attach("/rest/team",TeamListResource.class);

        router.attach("/rest/auth",Authentification.class);
        
        return router;
    }
}
