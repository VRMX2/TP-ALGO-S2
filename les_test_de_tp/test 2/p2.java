import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            DatagramSocket udpSocket = new DatagramSocket(2000);
            
            while (true) {
                // 1. Receive N from p1 via TCP
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int N = ois.readInt();
                socket.close();

                // 2. Compute S2
                int S2 = N * 2;
                System.out.println(S2);

                // 3. Call p3.maMethode1 via RMI
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MonInterface stub = (MonInterface) registry.lookup("NomService");
                stub.maMethode1(N, S2);

                // 4. Receive S4 via UDP from p3
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                int S4 = Integer.parseInt(msg.trim().split(",")[0]);

                // 5. Send S4 to p1 via TCP
                Socket socket1 = new Socket("localhost", 3000);
                ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
                oos.writeInt(S4);
                oos.flush();
                socket1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}