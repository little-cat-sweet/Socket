package Service;

import Common.Message;
import Constants.MsgType;

import java.io.*;

/**
 * @Author HongYun on 2023/3/4
 */

public class FileService {

    public void sendFile(String src, String dest, String sender, String receiver){

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setSrc(src);
        message.setDest(dest);
        message.setMsgType(MsgType.MESSAGE_FILE_SEND);

        byte[] fileBytes = new byte[(int) new File(src).length()];
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(src);
            try {
                fileInputStream.read(fileBytes);
//                将src文件读入到程序的字节数组
                message.setFileContent(fileBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(sender + " 发送文件到 " + receiver);

        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(ManageClientConnectServerThread.getThread(sender).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
