import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p3 extends UnicastRemoteObject implements MonInterface {

    protected p3() throws RemoteException {
        super();
    }

    @Override
    public void maMethode1(int N, int S2) throws RemoteException {
        int S3 = (S2 * 3) + N;
        System.out.println(S3);
        // Chain to maMethode2
        maMethode2(S3);
    }

    @Override
    public void maMethode2(int S3) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1111);
            MonInterface stub = (MonInterface) registry.lookup("NomService");
            stub.maMethode2(S3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void maMethode3(int S4) throws RemoteException {
        // Send S4 once via UDP to p2 (port 2000)
        try {
            DatagramSocket udpSocket = new DatagramSocket();
            String msg = S4 + ",";
            byte[] buffer = msg.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 2000);
            udpSocket.send(packet);
            udpSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            System.out.println("Registry 1099 already running.");
        }
        p3 obj = new p3();
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        reg.rebind("NomService", obj);
    }
}