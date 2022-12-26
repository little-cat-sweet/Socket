package FirstConnectTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author HongYun on 2022/12/26
 */

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9999);
        System.out.println("server starts listening ! ");

        Socket socket = server.accept();
        int readLength = 0;
        InputStream read = socket.getInputStream();
        byte[] buf = new byte[1024];
        while((readLength = read.read(buf)) != -1){
            System.out.println(new String(buf, 0, readLength));
        }
        System.out.println("server over ! ");

        //close source.
        read.close();
        server.close();
    }
}
