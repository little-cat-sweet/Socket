package SecondHomework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author HongYun on 2023/1/1
 */

public class UDP1 {

    public static void main(String[] args) throws IOException {

        //make socket.
        DatagramSocket socket = new DatagramSocket(9998);

        //make receive action.
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        System.out.println("9998 starts.");
        socket.receive(packet);
        int length = packet.getLength();
        byte[] receivedData = packet.getData();
        String receivedContent = new String(receivedData, 0, length);
        System.out.println("9998 received data is " + receivedContent);
        String response = "";
        if(receivedContent.equals("四大名族")){
            response = "当然有西游记啦 ~";
        }else {
            response = "你在说什么 ？";
        }

        //make sender action.
        byte[] sendBuf = response.getBytes();
        DatagramPacket sendPacket =
                new DatagramPacket(sendBuf, sendBuf.length, InetAddress.getByName("192.168.24.1"), 9999);
        socket.send(sendPacket);
        System.out.println("9998 send data");

        socket.close();
        System.out.println("9998 over");
    }
}
