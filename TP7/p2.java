import java.io.ObjectInputStream;
import java.net.*;

public class p2 extends Thread {

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            System.out.println("p2 listening on TCP 2000...");

            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int n = ois.readInt();
                System.out.println("Received N: " + n);
                socket.close();

                int s1 = n * 2;

                try (DatagramSocket udpSocket = new DatagramSocket()) {
                    String msg = n + "," + s1;
                    byte[] buffer = msg.getBytes();
                    InetAddress address = InetAddress.getByName("localhost");
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 3000);
                    udpSocket.send(packet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}