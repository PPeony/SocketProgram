package server;

import java.net.Socket;

public class User {

    private String id;
    private Socket socket;

    public User(String id, Socket socket) {
        this.id = id;
        this.socket = socket;
    }
    public String getId() {
        return id;
    }
    public Socket getSocket() {
        return socket;
    }
}
