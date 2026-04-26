import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class p3 {
    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket(2000);
            ServerSocket serverSocket = new ServerSocket(3000); 
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet); // bloque ici
                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.trim().split(","); // découper
                int N = Integer.parseInt(parts[0]);
               

                int S3 = N*3;
                System.out.println(S3);


                Socket socket = serverSocket.accept(); // bloque ici
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int S2 = ois.readInt();
                socket.close(); 

                int S4 = S2*3;
                System.out.println(S4);

                int s = S4+S3;


                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MonInterface stub = (MonInterface) registry.lookup("NomService");
                stub.maMethode(s); 

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}