package FirstHomework;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author HongYun on 2023/1/1
 */

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9999);
        System.out.println("server starts.");
        Socket socket = server.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String receivedData = reader.readLine();
        String response = "";
        if(receivedData.equals("name")){
            response = "亚索";
        }else if(receivedData.equals("hobby")){
            response = "喜欢E";
        }else {
            response = "excuse me ?";
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write(response);
        writer.newLine();
        writer.flush();
        System.out.println("server answered client's question.");
        System.out.println("server over.");

        writer.close();
        reader.close();
        socket.close();
    }
}
