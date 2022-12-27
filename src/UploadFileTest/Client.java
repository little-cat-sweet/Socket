package UploadFileTest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author HongYun on 2022/12/27
 */

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        System.out.println("client starts.");
        String img = "D:\\图片\\temp\\红玫瑰2.jpg";
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(img));
        byte[] content = Util.transferToByteArray(inputStream);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(content);
        System.out.println("client send img successfully ! ");
        socket.shutdownOutput();

        System.out.println("client get server massage : " + Util.streamToString(socket.getInputStream()));
        socket.shutdownInput();
        System.out.println("client over. ");

        bufferedOutputStream.close();
        inputStream.close();
        socket.close();

    }
}
