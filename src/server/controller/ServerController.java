package server.controller;

import server.view.ServerStatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Controller for server
 * Assigned to @Ramon Jasmin
 * */
public class ServerController {
    Server server;
    ServerStatusView serverStatusView;

    boolean serverStatus = false;

    public ServerController(Server server, ServerStatusView serverStatusView){
        this.server = server;
        this.serverStatusView = serverStatusView;

        serverStatusView.setServerListener(new serverListener());
    }

    class serverListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!serverStatus){
                serverStatusView.setOnline();
                serverStatus = true;
                try {
                    System.out.println("Set online");
                    Server server =new Server(new InetSocketAddress(2069));
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    //executor.submit(server);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                System.out.println("Set offline");
                serverStatusView.setOffline();
                serverStatus= false;
                //server.terminate();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ServerStatusView view = new ServerStatusView();
        Server server = new Server(new InetSocketAddress(2020));
        ServerController controller = new ServerController(server, view);
    }
}
