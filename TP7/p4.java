import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p4 extends UnicastRemoteObject implements P4Interface {

    protected p4() throws RemoteException {
        super();
    }

    @Override
    public void processS2(int s2) throws RemoteException {
        System.out.println("s2 received from p3 (RMI): " + s2);
        int s3 = s2 * 4;
        System.out.println("s3 calculated: " + s3);

        try {
            Socket socket = new Socket("localhost", 4000);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeInt(s3);
            oos.flush();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            p4 obj = new p4();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("P4Service", obj);
            System.out.println("p4 RMI server ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}