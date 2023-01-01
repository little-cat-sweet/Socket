package SecondHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author HongYun on 2023/1/1
 */

public class UDP2 {

    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket(9999);

        BufferedReader terminalReader =
                new BufferedReader(new InputStreamReader(System.in));
        String question = terminalReader.readLine();
        byte[] sendContent = question.getBytes();
        DatagramPacket sendPacket =
                new DatagramPacket(sendContent, sendContent.length, InetAddress.getByName("192.168.24.1"), 9998);
        socket.send(sendPacket);
        System.out.println("9999 send data");

        byte[] receiveContent = new byte[1024];
        DatagramPacket receivePacket =
                new DatagramPacket(receiveContent, receiveContent.length);
        System.out.println("9999 receive.");
        socket.receive(receivePacket);
        int length = receivePacket.getLength();
        byte[] receivedData = receivePacket.getData();
        String receivedContent = new String(receivedData, 0, length);
        System.out.println("9999 received data is " + receivedContent);

        socket.close();
        System.out.println("9999 over.");
    }
}
