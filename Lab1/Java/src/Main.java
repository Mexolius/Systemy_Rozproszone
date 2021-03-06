import Client.ClientUDP;
import Server.ServerUDP;

public class Main {
    public static void main(String[] args) {
        ClientUDP client = new ClientUDP(9877, 9876);
        ServerUDP server = new ServerUDP(9876);

        server.start();
        client.start();
    }
}
