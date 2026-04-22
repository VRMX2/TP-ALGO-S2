import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Scanner;

public class p1{
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            DatagramSocket udpSocket = new DatagramSocket(5000); // PORT obligatoire
            while (true) {
                System.out.println("entrer la valeur de N:");
                int N = scanner.nextInt();

                Socket socket = new Socket("localhost", 1000);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(N);
                oos.flush();
                socket.close();

                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet); // bloque ici
                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.trim().split(","); // découper
                int S2 = Integer.parseInt(parts[0]);
                System.out.println(S2);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}