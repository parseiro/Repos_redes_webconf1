package utfpr.edu.br.ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Util {
    // A utility method to convert the byte array
    // data into a string representation.
    public static String data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret.toString();
    }

    public static void sendUdpPacket(String text, InetAddress address, int port, DatagramSocket socket) throws IOException {
        // convert the String input into the byte array.
        var buf = text.getBytes();

        // Step 2 : Create the datagramPacket for sending the data
        var DpSend = new DatagramPacket(buf, buf.length, address, port);

        // Step 3 : invoke the send call to actually send the data
        socket.send(DpSend);
    }


    public static DatagramPacket receiveUdpPacket(DatagramSocket socket, int timeout) throws IOException {
        DatagramPacket DpReceive;
        byte[] receive = new byte[65535];
        DpReceive = new DatagramPacket(receive, receive.length);

        // Step 3 : revieve the data in byte buffer.
        socket.setSoTimeout(timeout);
        socket.receive(DpReceive);
        return DpReceive;
    }
}
