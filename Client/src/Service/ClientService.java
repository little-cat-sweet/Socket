package Service;

import java.io.IOException;

/**
 * @Author HongYun on 2023/1/2
 */

public interface ClientService {

    boolean checkUser(String userId, String password);

    void getOnlineUserReq() throws IOException;
}
