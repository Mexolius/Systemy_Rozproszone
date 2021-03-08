package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class User {
    private final Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private String nickname;

    public User(Socket socket) {
        this.socket = socket;
        try {
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User(Socket socket, String nickname) {
        this(socket);
        this.setNickname(nickname);
    }

    public void setNickname(String nickname){
        this.nickname=nickname;
    }

    public String getNickname(){
        return this.nickname;
    }

    public String read() throws IOException {
        return this.input.readLine();
    }

    public void write(String message) {
        this.output.println(message);
    }

    public InetAddress getAddress() {
        return this.socket.getInetAddress();
    }

    public int getPort() {
        return this.socket.getPort();
    }

}
