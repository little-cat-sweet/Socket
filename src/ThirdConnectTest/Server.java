package ThirdConnectTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author HongYun on 2022/12/27
 */

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9999);
        System.out.println("server start");
        Socket socket = server.accept();

        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(reader.readLine());

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("hello client char");
        writer.newLine();
        writer.flush();

        System.out.println("server over");
        writer.close();
        reader.close();
        server.close();
        socket.close();
    }
}
