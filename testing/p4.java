import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class p4{
    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket(2000);
            DatagramSocket udpSocket1 = new DatagramSocket();
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet); // bloque ici
                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.trim().split(","); // découper
                int N = Integer.parseInt(parts[0]); 
                int S2 = Integer.parseInt(parts[1]); 
                int S3 = Integer.parseInt(parts[2]); 

                int S4 = N*4;
                System.err.println(S4);

                int s = (S2+S3+S4);

                String msg1 = s+","; 
                byte[] buffer1 = msg1.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length, address, 3000);
                udpSocket1.send(packet1);
                udpSocket1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}