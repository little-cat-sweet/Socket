package UdpOne;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Author HongYun on 2022/12/31
 */

public class Receiver {

    public static void main(String[] args) throws IOException {

        //Using to get data or send(because the role could change.), which from port 9999.
        DatagramSocket socket = new DatagramSocket(9999);

        //Using this packet to receive data, and transfer it to socket.
        byte[] pack = new byte[1024];
        DatagramPacket packet = new DatagramPacket(pack, pack.length);

        System.out.println("receiver starts waiting for data.");
        //If no data coming, then stop continue until data come.
        socket.receive(packet);
        socket.close();

        //Could unfold the packet, and get the data from it.

        //The real data length from receiving.
        int length = packet.getLength();
        byte[] dataPacket = packet.getData();
        String data = new String(dataPacket, 0, length);
        System.out.println("9999 received data : " + data);
    }
}
