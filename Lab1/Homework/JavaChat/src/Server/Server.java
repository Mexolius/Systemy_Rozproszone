package Server;

import Data.User;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<User> users = new ArrayList<>();
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {

            try {
                while (true) {
                    registerUser(serverSocket.accept());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private void registerUser(Socket userSocket) {
        User u= new User(userSocket);
        users.add(u);
        u.write("connected to server, thank you for joining the BestChat");



        new Thread(()->{
            while(true){
                try {
                    System.out.println(u.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void broadcastMessage(String message, InetAddress addrFrom, int portFrom) {
        this.users.stream().filter(user->!(user.getAddress().equals(addrFrom)&&user.getPort()!=portFrom)).forEach(user->user.write(message));
    }

    public static void main(String[] args) {
        new Server(12345);
    }
}
