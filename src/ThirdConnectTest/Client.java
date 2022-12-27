package ThirdConnectTest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author HongYun on 2022/12/27
 */

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("client starts");
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("hello server (char)");
        writer.newLine();
        writer.flush();

        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(reader.readLine());

        System.out.println("client over");
        reader.close();
        writer.close();
        socket.close();
    }
}
