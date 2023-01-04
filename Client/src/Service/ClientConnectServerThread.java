package Service;

import Common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @Author HongYun on 2023/1/2
 */

public class ClientConnectServerThread extends Thread{

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public ClientConnectServerThread(Socket socket){

        this.setSocket(socket);
    }

    public ClientConnectServerThread(){

    }

    @Override
    public void run() {
        while (true){
            ObjectInputStream read = null;
            try {
                read = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Message message = null;
            try {
                System.out.println("client is waiting for server's response.");
                //thread will wait until get the message from server.
                assert read != null;
                message = (Message) read.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
