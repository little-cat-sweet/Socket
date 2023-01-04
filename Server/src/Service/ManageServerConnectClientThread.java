package Service;

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
}
