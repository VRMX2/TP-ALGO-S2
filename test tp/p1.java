import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p1 extends UnicastRemoteObject implements MonInterface {
    protected p1() throws RemoteException {
        super();
    }

 @Override public void maMethode(int s) throws RemoteException {
    System.out.println(s);
 }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        p1 obj = new p1();
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        reg.rebind("NomService", obj);

        try {
            Scanner scanner = new Scanner(System.in);
            DatagramSocket udpSocket = new DatagramSocket();
            while (true) {
                System.out.println("entrer la valeur de N:");
                int N = scanner.nextInt();

                Socket socket = new Socket("localhost", 1000);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(N);
                oos.flush();
                socket.close();

                String msg = N + ",";
                byte[] buffer = msg.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 2000);
                udpSocket.send(packet);

                Thread.sleep(300);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}