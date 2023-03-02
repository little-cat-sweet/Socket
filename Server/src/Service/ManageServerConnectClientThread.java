package Service;

import Common.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
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

    public static void sendMessageToOne(Message message){

        try {
            ObjectOutputStream writer = new ObjectOutputStream(getThread(message.getSender()).getSocket().getOutputStream());
            writer.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
