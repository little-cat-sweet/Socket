package UdpOne;

import java.io.IOException;
import java.net.*;

/**
 * @Author HongYun on 2022/12/31
 */

public class Sender {

    public static void main(String[] args) throws IOException {

        // sender data or receive data(because the role could change.) on 9998
        DatagramSocket socket = new DatagramSocket(9998);

        byte[] data = "9998 : let's eat hotspot tomorrow ~".getBytes();
        DatagramPacket packet =
                new DatagramPacket(data, data.length, InetAddress.getByName("192.168.24.1"), 9999);

        socket.send(packet);
        socket.close();
        System.out.println("Send data finished.");
    }
}
