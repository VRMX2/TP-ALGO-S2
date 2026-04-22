import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class p3 {
    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket(2000);
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet); 
                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.trim().split(","); 
                int N = Integer.parseInt(parts[0]);
                int S2 = Integer.parseInt(parts[1]);

                int S3 = (S2*3 )+N;
                System.out.println(S3);

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MonInterface stub = (MonInterface) registry.lookup("NomService");
                stub.maMethode(S3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
