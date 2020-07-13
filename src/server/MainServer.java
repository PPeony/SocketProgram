package server;

public class MainServer {

    public static void main(String[] args){
        Server server = Server.getInstance();
        server.setPort(Properties.port);
        server.startServer();
    }
}
