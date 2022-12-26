package SecondConnectTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author HongYun on 2022/12/26
 */

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9999);
        System.out.println("server start ! ");

        Socket socket = server.accept();

        int readLength = 0;
        InputStream read = socket.getInputStream();
        byte[] buf = new byte[1024];
        while((readLength = read.read(buf)) != -1){
            System.out.println(new String(buf, 0, readLength));
        }
        socket.shutdownInput();
        System.out.println("server read shutdown.");

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello client".getBytes());
        socket.shutdownOutput();
        System.out.println("server write shutdown.");

        read.close();
        outputStream.close();
        socket.close();
        server.close();
    }
}
