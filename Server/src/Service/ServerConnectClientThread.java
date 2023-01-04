package Service;

import Common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @Author HongYun on 2023/1/4
 */

public class ServerConnectClientThread extends Thread{

    private String userId;
    private Socket socket;

    public ServerConnectClientThread(String userId, Socket socket) {
        this.userId = userId;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("server read message from client");
            ObjectInputStream read = null;
            try {
                read = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) read.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
