package server;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class Launcher extends Component {
	
	public static void main(String[] args) throws Exception {
        new Launcher().start();
    }

    public Launcher() {
        Server server = new Server(Protocol.HTTP, 8000);
        getServers().add(server);
        //server.getContext().getParameters().set("tracing", "true");

        Base.open();
        getClients().add(Protocol.CLAP);

        getDefaultHost().attachDefault(new RegattaApplication());
        
        System.out.println("Server started on port 8000.");
        System.out.println("Application is now available on http://localhost:8000/web/index.html");
    }
}
