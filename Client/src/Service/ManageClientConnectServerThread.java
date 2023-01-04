package Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author HongYun on 2023/1/4
 */

public class ManageClientConnectServerThread {

    private static ConcurrentHashMap<String, ClientConnectServerThread> manageThreads = new ConcurrentHashMap<>();

    public static void addThread(String userId, ClientConnectServerThread clientConnectServerThread){
        manageThreads.put(userId, clientConnectServerThread);
    }

    public static ClientConnectServerThread getThread(String userId){

        return manageThreads.get(userId);
    }
}
