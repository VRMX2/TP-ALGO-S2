import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class p2 {
    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket(1000);
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet); 
                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.trim().split(","); 
                int N = Integer.parseInt(parts[0]);

                int S2 = N*2;
                System.out.println("S2 = " +S2);

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MonInterface stub = (MonInterface) registry.lookup("NomService");
                stub.maMethode(N,S2); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}