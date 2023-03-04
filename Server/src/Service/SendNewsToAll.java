package Service;

import Common.Message;
import Constants.MsgType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @Author HongYun on 2023/3/4
 */

public class SendNewsToAll implements Runnable{

    @Override
    public void run() {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("");
        while (true){

            System.out.println("请输入你想通知的信息或者输入exit，退出通知功能");
            String action = null;
            try {
                action = read.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if("exit".equals(action)){
                break;
            }

            Message message = new Message();
            message.setMsgType(MsgType.MESSAGE_NOTIFY_ALL);
            message.setContent("server speak : " + action);
            List<ServerConnectClientThread> onlineUsers = ManageServerConnectClientThread.getAllOnlineThreads();
            for(ServerConnectClientThread onlineUser : onlineUsers){
                try {
                    ObjectOutputStream writer = new ObjectOutputStream(onlineUser.getSocket().getOutputStream());
                    writer.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
