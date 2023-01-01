package UdpOne;

import java.io.IOException;
import java.net.*;

/**
 * @Author HongYun on 2022/12/31
 */

public class UDP2 {

    public static void main(String[] args) throws IOException {

        // sender data or receive data(because the role could change.) on 9998
        DatagramSocket socket = new DatagramSocket(9998);

        System.out.println("9998 send data.");
        byte[] data = "9998 : let's eat hotspot tomorrow ~".getBytes();
        DatagramPacket packet =
                new DatagramPacket(data, data.length, InetAddress.getByName("192.168.24.1"), 9999);

        socket.send(packet);


        byte[] pack = new byte[1024];
        DatagramPacket packetReceiver = new DatagramPacket(pack, pack.length);

        System.out.println("9998 starts waiting for data.");
        //If no data coming, then stop continue until data come.
        socket.receive(packetReceiver);

        //Could unfold the packet, and get the data from it.

        //The real data length from receiving.
        int length = packetReceiver.getLength();
        byte[] dataPacket = packetReceiver.getData();
        String receiverData = new String(dataPacket, 0, length);
        System.out.println("9998 received data : " + receiverData);
        System.out.println("9998 close.");
        socket.close();
    }
}
