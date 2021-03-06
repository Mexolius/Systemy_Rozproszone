package UDP;

import java.net.DatagramSocket;
import java.net.SocketException;

public abstract class UDPUser extends Thread{

    protected DatagramSocket socket;

    public UDPUser(int port){
        super();

        try {
            this.socket= new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


}
