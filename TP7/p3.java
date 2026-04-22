import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class p3 extends Thread {

    public void run() {
        try (DatagramSocket socket = new DatagramSocket(3000)) {
            System.out.println("p3 waiting UDP on port 3000...");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.split(",");
                int n = Integer.parseInt(parts[0].trim());
                int s1 = Integer.parseInt(parts[1].trim());
                System.out.println("Received N=" + n + ", s1=" + s1);

                int s2 = (s1 * 3) + n;
                System.out.println("s2 = " + s2);

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                P4Interface stub = (P4Interface) registry.lookup("P4Service");
                stub.processS2(s2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}