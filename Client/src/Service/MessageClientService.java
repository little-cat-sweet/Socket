package Service;

import Common.Message;
import Constants.MsgType;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Author HongYun on 2023/3/2
 */

public class MessageClientService {

    public void sendMessageToOne(String content, String senderId, String getterId){

        Message message = new Message();
        message.setSender(senderId);
        message.setReceiver(getterId);
        message.setContent(content);
        message.setMsgType(MsgType.MESSAGE_SEND_ONE);
        ObjectOutputStream writer = null;
        try {
            writer =  new ObjectOutputStream(
                    ManageClientConnectServerThread.
                    getThread(senderId).
                    getSocket().getOutputStream());
            writer.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
