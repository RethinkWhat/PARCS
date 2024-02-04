package server.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.Map;

public class Server{
    private ServerSocket server = new ServerSocket();

    private BufferedReader reader;

    private PrintWriter writer;

    private XMLParser xmlParser;

    public Server(SocketAddress socketAddress) throws IOException {
        server.bind(socketAddress);
        xmlParser = new XMLParser();
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    synchronized public void read() {
        //reader = new BufferedReader()
    }

    public boolean validateAccount(String account) {
        String[] userAccount = account.split(",");

        Map<String, String> users = xmlParser.getUserLoginCredentials();

        String password = users.getOrDefault(userAccount[0], null);
        return (password != null && password.equals(userAccount[1]));




    }

}
