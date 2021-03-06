package Client;

import UDP.UDPUser;

import java.net.DatagramPacket;
import java.net.InetAddress;


public class ClientUDP extends UDPUser {

    private final int serverPort;

    public ClientUDP(int port, int serverPort){
        super(port);
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        super.run();

        try {
            InetAddress address = InetAddress.getByName("localhost");
            byte[] sendBuffer = "Ping".getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendBuffer, sendBuffer.length, address, this.serverPort);
            this.socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveBuffer, receiveBuffer.length);
            this.socket.receive(receivePacket);
            String msg = new String(receivePacket.getData());
            System.out.println("Message on client: " + msg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (this.socket != null) this.socket.close();
        }

    }
}
