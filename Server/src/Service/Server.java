package Service;

import Common.Message;
import Common.User;
import Constants.MsgType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author HongYun on 2023/1/4
 */

public class Server {

    private ServerSocket serverSocket = null;
    public Server(){
        System.out.println("server is listening on port 9999");
        try {
            serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            ObjectInputStream read = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            User user = (User) read.readObject();
            Message message = new Message();
            if(user.getUserId().equals("111") && user.getPassword().equals("123456")){
                message.setMsgType(MsgType.MESSAGE_LOGIN_SUCCESS);
                writer.writeObject(message);
                ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(user.getUserId(), socket);
                serverConnectClientThread.start();
                ManageServerConnectClientThread.addThread(user.getUserId(), serverConnectClientThread);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
