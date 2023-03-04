package Service;

import Common.Message;
import Constants.MsgType;

import java.io.FileOutputStream;
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
                //thread will wait until get the message from server.
                assert read != null;
                message = (Message) read.readObject();
                if(message.getMsgType().equals(MsgType.MESSAGE_Return_ONLINE_USER)){
                    System.out.println("===========online users=============");
                    String[] userIds = message.getContent().split(" ");
                    for(String userId : userIds){
                        System.out.println("用户 : " + userId);
                    }
                }else if(message.getMsgType().equals(MsgType.MESSAGE_SEND_ONE)){
                    System.out.println(message.getSender() + ": " + message.getContent());
                }else if(message.getMsgType().equals(MsgType.MESSAGE_SEND_ALL)){
                    System.out.println(message.getSender() + ": " + message.getContent());
                }else if(message.getMsgType().equals(MsgType.MESSAGE_FILE_SEND)){
                    System.out.println("收到：" + message.getSender() + "发来的邮件，并保存到" + message.getDest());
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getDest());
                    fileOutputStream.write(message.getFileContent());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    System.out.println("保存成功");
                }else if(message.getMsgType().equals(MsgType.MESSAGE_NOTIFY_ALL)){
                    System.out.println(message.getContent());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
