package UploadFileTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author HongYun on 2022/12/27
 */

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("server listening. ");
        Socket socket = serverSocket.accept();

        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        byte[] content = Util.transferToByteArray(bufferedInputStream);
        String imgLocation = "src\\queen.jpg";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(imgLocation));
        bufferedOutputStream.write(content);
        System.out.println("server get img successfully ! ");

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String letter = "Hi client, I have got the content.";
        bufferedWriter.write(letter);
        bufferedWriter.flush();
        socket.shutdownOutput();
        System.out.println("server over. ");

        bufferedWriter.close();
        bufferedOutputStream.close();
        bufferedInputStream.close();
        socket.close();
        serverSocket.close();
    }
}
