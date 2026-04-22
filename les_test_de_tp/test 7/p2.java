import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class p2 {
    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket();
            ServerSocket serverSocket1 = new ServerSocket(4000);
            ServerSocket serverSocket = new ServerSocket(1000);
            while (true) {
                Socket socket = serverSocket.accept(); 
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int N = ois.readInt();
                socket.close();

                int S1= N*2;
                System.out.println(S1);

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MonInterface stub = (MonInterface) registry.lookup("NomService");
                stub.maMethode(N,S1);


                
                Socket socket1 = serverSocket1.accept(); 
                ObjectInputStream ois1 = new ObjectInputStream(socket1.getInputStream());
                int S2 = ois1.readInt();
                System.out.println(S2);
                socket1.close(); 



                String msg = S2+","; 
                byte[] buffer = msg.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                udpSocket.send(packet); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}