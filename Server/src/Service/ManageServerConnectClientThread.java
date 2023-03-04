package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author HongYun on 2023/1/4
 */

public class ManageServerConnectClientThread {

    private static final ConcurrentHashMap<String, ServerConnectClientThread> manageThreads = new ConcurrentHashMap<>();

    public static void addThread(String userId, ServerConnectClientThread thread){
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
}
