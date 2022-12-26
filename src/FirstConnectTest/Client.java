package FirstConnectTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author HongYun on 2022/12/26
 */

public class Client {

    public static void main(String[] args) throws IOException {

        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println();

        OutputStream write = client.getOutputStream();
        write.write("hello world".getBytes());
        System.out.println("client over");
        write.close();
        client.close();

    }
}
