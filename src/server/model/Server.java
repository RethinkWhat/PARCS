package server.model;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
    private ServerSocket server;

    public Server(SocketAddress socketAddress) throws IOException {
        server = new ServerSocket();
        server.bind(socketAddress);
    }

    public ServerSocket getServer() {
        return server;
    }

    public static void main(String[] args) {
        // TODO: change address
        SocketAddress address = new InetSocketAddress(2020);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            Server server = new Server(address);

            while (true) {
                Socket clientSocket = server.getServer().accept();
                new Thread(new ClientHandler(clientSocket)).start();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
