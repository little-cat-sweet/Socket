package Service;

import Common.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author HongYun on 2023/1/4
 */

public class ManageServerConnectClientThread {

    private static final List<Message> offLineMessage = new ArrayList<>();

    private static final ConcurrentHashMap<String, ServerConnectClientThread> manageThreads = new ConcurrentHashMap<>();

    public static void addThread(String userId, ServerConnectClientThread thread){
        if(hasOffLineMessage()){
            System.out.println("搜索离线信息");
            for(Message message : offLineMessage){
                if(message.getReceiver().equals(userId)){
                    try {
                        ObjectOutputStream writer = new ObjectOutputStream(thread.getSocket().getOutputStream());
                        writer.writeObject(message);
                        System.out.println("发送离线信息到: " + userId);
                        offLineMessage.remove(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
        manageThreads.put(userId, thread);
    }

    public static ServerConnectClientThread getThread(String userId){

        return manageThreads.get(userId);
    }

    public static ConcurrentHashMap<String, ServerConnectClientThread> getManageThreads(){
        return manageThreads;
    }

    public static void removeServerConnectClientThread(String userId){

        manageThreads.remove(userId);
    }

    public static List<ServerConnectClientThread> getReceivers(String senderId){

        List<ServerConnectClientThread> receivers = new ArrayList<>();
        for(String key : manageThreads.keySet()){
            if(key.equals(senderId)) continue;
            receivers.add(manageThreads.get(key));
        }
        return receivers;
    }

    public static List<ServerConnectClientThread> getAllOnlineThreads(){

        List<ServerConnectClientThread> onlineUsers = new ArrayList<>();
        for(String key : manageThreads.keySet()){
            onlineUsers.add(manageThreads.get(key));
        }
        return onlineUsers;
    }

    public static void addOffLineMessage(Message message){
        System.out.println("为" + message.getReceiver() + "保留离线信息");
        offLineMessage.add(message);
    }

    public static boolean hasOffLineMessage(){

        return offLineMessage.size() != 0;
    }
}
