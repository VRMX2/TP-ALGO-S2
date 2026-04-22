import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class p3{
    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket(1000);
            DatagramSocket udpSocket1 = new DatagramSocket();
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet); 
                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.trim().split(","); 
                int N = Integer.parseInt(parts[0]);
                int S2 = Integer.parseInt(parts[1]);

                int S3 = S2*N;
                System.out.println(S3);

                String msg1 = S3+","; 
                byte[] buffer1 = msg1.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length, address, 2000);
                udpSocket1.send(packet1); 

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}