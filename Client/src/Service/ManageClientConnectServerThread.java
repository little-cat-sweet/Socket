package Service;

import java.util.ArrayList;
import java.util.List;
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

    public static List<ClientConnectServerThread> getAllThread(String sender){

        List<ClientConnectServerThread> res = new ArrayList<>();
        for(String key : manageThreads.keySet()){
            if(key.equals(sender)) continue;
            res.add(manageThreads.get(key));
        }
        return res;
    }
}
