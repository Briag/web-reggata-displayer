package server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class RegattaApplication extends Application {

    public RegattaApplication() {
        setName("Web Regatta");
        setDescription("RESTlet + AngularJS integration");
        setOwner("Le Nabec Briag / Alexandre Hoyet");
        setAuthor("Le Nabec Briag / Alexandre Hoyet");
    }

    @Override
    public Restlet createInboundRoot() {
        Directory directory = new Directory(getContext(), "clap://system/resources/static/");
        directory.setDeeplyAccessible(true);

        System.out.println(directory.getRootRef().getPath());
        Router router = new Router(getContext());
        router.attach("/web", directory);
        return router;
    }
}
