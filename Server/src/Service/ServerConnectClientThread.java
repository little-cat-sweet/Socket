package Service;

import Common.Message;
import Constants.MsgType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("server reading message from client : " + userId);
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
                }else if(readMessage.getMsgType().equals(MsgType.MESSAGE_CLIENT_EXIT)){
                    System.out.println(readMessage.getSender() + " logout system");
                    ManageServerConnectClientThread.removeServerConnectClientThread(readMessage.getSender());
                    break;
                }else if(readMessage.getMsgType().equals(MsgType.MESSAGE_SEND_ONE)){
                    ServerConnectClientThread serverConnectClientThread = ManageServerConnectClientThread.getThread(readMessage.getReceiver());
                    if(serverConnectClientThread == null){
                        ManageServerConnectClientThread.addOffLineMessage(readMessage);
                        continue;
                    }
                    writer = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    writer.writeObject(readMessage);
                    System.out.println("已转发数据从" + readMessage.getSender() + "，到" + readMessage.getReceiver());
                }else if(readMessage.getMsgType().equals(MsgType.MESSAGE_SEND_ALL)){
                    String sender = readMessage.getSender();
                    content = readMessage.getContent();
                    List<ServerConnectClientThread> receivers = ManageServerConnectClientThread.getReceivers(sender);
                    Message message = new Message();
                    message.setSender(sender);
                    message.setContent(content);
                    message.setMsgType(MsgType.MESSAGE_SEND_ALL);
                    for(ServerConnectClientThread receiver : receivers){
                        writer = new ObjectOutputStream(receiver.getSocket().getOutputStream());
                        writer.writeObject(message);
                    }
                }else if(readMessage.getMsgType().equals(MsgType.MESSAGE_FILE_SEND)){
                    writer = new ObjectOutputStream(ManageServerConnectClientThread.getThread(readMessage.getReceiver()).getSocket().getOutputStream());
                    writer.writeObject(readMessage);
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
