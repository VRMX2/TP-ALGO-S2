import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class p2 {
    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(1000);
            DatagramSocket udpSocket = new DatagramSocket(5000); // PORT obligatoire
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int N = ois.readInt();
                socket.close();

                int S2 = N * 2;
                System.out.println(S2);

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MonInterface stub = (MonInterface) registry.lookup("NomService");
                stub.maMethode1(N, S2);

                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet); 
                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.trim().split(","); 
                int S4 = Integer.parseInt(parts[0]);

                Registry registry1 = LocateRegistry.getRegistry("localhost", 1112);
                MonInterface stub1 = (MonInterface) registry1.lookup("NomService");
                stub1.maMethode3(S4);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}