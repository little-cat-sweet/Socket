package Service;

import Common.Message;
import Common.User;
import Constants.MsgType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author HongYun on 2023/1/4
 */

public class Server {

    private ServerSocket serverSocket = null;

    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();

    static {
        validUsers.put("111", new User("111", "45454"));
        validUsers.put("222", new User("222", "312654"));
        validUsers.put("333", new User("333", "123456"));

    }
    public Server(){
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server is listening on port 9999");
        try {
            while (true){
                Socket socket = serverSocket.accept();
                ObjectInputStream read = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
                User user = (User) read.readObject();
                Message message = new Message();
                if(isValidUser(user)){
                    System.out.println("server : " + user.getUserId() + " login in successful.");
                    message.setMsgType(MsgType.MESSAGE_LOGIN_SUCCESS);
                    writer.writeObject(message);
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(user.getUserId(), socket);
                    serverConnectClientThread.start();
                    ManageServerConnectClientThread.addThread(user.getUserId(), serverConnectClientThread);
                }else{
                    System.out.println("server : " + user.getUserId() + " login in failed.");
                    message.setMsgType(MsgType.MESSAGE_LOGIN_FAIL);
                    writer.writeObject(message);
                    socket.close();
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isValidUser(User user){

        if(! validUsers.containsKey(user.getUserId())){
            return false;
        }
        User validUser = validUsers.get(user.getUserId());
        return validUser.getPassword().equals(user.getPassword());
    }
}
