package Client;

import Data.User;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private User user;

    private Client(String address, int port, String nickname){
        try {
            this.user = new User(new Socket(address, port), nickname);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            Scanner in = new Scanner(System.in);
            while(true){
                this.user.write(in.nextLine());
            }
        }).start();

        new Thread(()->{
            try {
                while(true){
                    System.out.println(user.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        new Client("localhost", 12345, "ZbychuGitara2323");
    }
}
