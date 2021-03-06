package Server;

import UDP.UDPUser;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ServerUDP extends UDPUser {

    public ServerUDP(int port){
        super(port);

    }

    public void run() {
        super.run();

        DatagramSocket socket = null;
        try {
            byte[] replyBuffer = "Pong".getBytes();
            byte[] receiveBuffer = new byte[1024];
            while(true) {

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveBuffer, receiveBuffer.length);
                this.socket.receive(receivePacket);
                String msg = new String(receivePacket.getData());
                System.out.println("Message on server: " + msg);
                if(msg.startsWith("int")){
                    this.socket.receive(receivePacket);
                    ByteBuffer wrapped = ByteBuffer.wrap(receivePacket.getData());
                    int data = wrapped.order(ByteOrder.LITTLE_ENDIAN).getInt();
                    System.out.println("Int on server: " + data);
                    byte[] buff = ByteBuffer.allocate(4).putInt(++data).array();
                    this.socket.send(new DatagramPacket(buff, buff.length,receivePacket.getAddress(),receivePacket.getPort()));

                }
                Arrays.fill(receiveBuffer,(byte)0);

                DatagramPacket reply = new DatagramPacket(replyBuffer,replyBuffer.length,receivePacket.getAddress(), receivePacket.getPort());
                this.socket.send(reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (this.socket != null) this.socket.close();
        }
    }
}
