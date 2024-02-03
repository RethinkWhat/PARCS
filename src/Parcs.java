import client.model.Client;
import server.model.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parcs implements Runnable {

    SocketAddress socketAddress = new InetSocketAddress(2000);

    ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public void run() {
        try {
            Server server = new Server(socketAddress);
            while (true) {
                Client client = new Client(server.getServer());
                executor.submit(client);
            }
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) throws Exception {
        new Parcs().run();
    }
}
