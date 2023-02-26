package Service;

import Common.Message;
import Constants.MsgType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            System.out.println("server read message from client : " + userId);
            ObjectInputStream read = null;
            ObjectOutputStream writer = null;
            try {
                read = new ObjectInputStream(socket.getInputStream());
                writer = new ObjectOutputStream(socket.getOutputStream());
                Message readMessage = (Message) read.readObject();
                Message writeMessage = new Message();
                String content = null;
                if(readMessage.getMsgType().equals(MsgType.MESSAGE_GET_ONLINE_USER)){
                    content = getOnlineUsers();
                    writeMessage.setMsgType(MsgType.MESSAGE_Return_ONLINE_USER);
                    writeMessage.setContent(content);
                    writer.writeObject(writeMessage);
                    System.out.println("Having send all online users information to client" + userId);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String getOnlineUsers(){

        StringBuilder onlineUsers = new StringBuilder();
        for(String key : ManageServerConnectClientThread.getManageThreads().keySet()){
            onlineUsers.append(key).append(" ");
        }
        return onlineUsers.toString();
    }
}
