import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class p2 {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(1000); 
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int N = ois.readInt();
                socket.close(); 

                int S2 = N*2;
                System.err.println(S2);

                DatagramSocket udpSocket = new DatagramSocket();
                String msg = N+","+S2; 
                byte[] buffer = msg.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 2000);
                udpSocket.send(packet); 
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
