import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class p1{
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DatagramSocket udpSocket = new DatagramSocket();
            ServerSocket serverSocket = new ServerSocket(2000);
            while (true) {
                System.out.println("entrer la valeur de N:");
                int N = scanner.nextInt();

                System.out.println("N = " +N);
            
                String msg = N+","; 
                byte[] buffer = msg.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 1000);
                udpSocket.send(packet);


                Socket socket = serverSocket.accept(); 
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int S4 = ois.readInt();
                System.out.println("S4 = " +S4);
                socket.close();




            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}