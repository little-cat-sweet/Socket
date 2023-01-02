package Service.impl;

import Common.Message;
import Common.User;
import Constants.MsgType;
import Service.ClientService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author HongYun on 2023/1/2
 */

public class ClientServiceImpl implements ClientService {

    private User user = new User();
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean checkUser(String userId, String password){

        user.setUserId(userId);
        user.setPassword(password);
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        try {
            writer = new ObjectOutputStream(socket.getOutputStream());
            writer.writeObject(user);

            reader = new ObjectInputStream(socket.getInputStream());
            Message message = null;
            try {
                message = (Message) reader.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(message.getMsgType().equals(MsgType.MESSAGE_LOGIN_SUCCESS)){
                // todo create a thread to handle it.
            }else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }



}
