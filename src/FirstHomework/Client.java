package FirstHomework;

import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author HongYun on 2023/1/1
 */

public class Client {

    public static void main(String[] args) throws IOException {

        //making a client socket
        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("client starts");

        //making a writer by char.
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        String question = terminalReader.readLine();
        writer.write(question);
        writer.newLine();
        writer.flush();


        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String receivedData = reader.readLine();
        System.out.println("server response : " + receivedData);
        System.out.println("client over.");

        writer.close();
        reader.close();
        client.close();
    }
}
