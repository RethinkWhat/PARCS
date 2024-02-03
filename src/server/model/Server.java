package server.model;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class Server{
    private ServerSocket server = new ServerSocket();

    public Server(SocketAddress socketAddress) throws IOException {
        server.bind(socketAddress);
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

}
