package SecondConnectTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author HongYun on 2022/12/26
 */

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("client starts.");

        OutputStream write = socket.getOutputStream();
        write.write("hello server".getBytes());
        socket.shutdownOutput();
        System.out.println("client write close.");

        InputStream read = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLength = 0;
        while((readLength = read.read(buf)) != -1){
            System.out.println(new String(buf, 0, readLength));
        }
        socket.shutdownInput();
        System.out.println("client read close.");

        read.close();
        write.close();
        socket.close();
    }
}
