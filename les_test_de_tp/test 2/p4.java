import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p4 extends UnicastRemoteObject implements MonInterface {

    protected p4() throws RemoteException {
        super();
    }

    @Override
    public void maMethode1(int N, int S2) throws RemoteException {
    }

    @Override
    public void maMethode2(int S3) throws RemoteException {
        int S4 = S3 * 4;
        System.out.println(S4);
        // Call back p3's maMethode3 to send S4 via UDP
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MonInterface stub = (MonInterface) registry.lookup("NomService");
            stub.maMethode3(S4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void maMethode3(int S4) throws RemoteException {
    }

    public static void main(String[] args) throws Exception {
        try {
            LocateRegistry.createRegistry(1111);
        } catch (Exception e) {
            System.out.println("Registry 1111 already running.");
        }
        p4 obj = new p4();
        Registry reg = LocateRegistry.getRegistry("localhost", 1111);
        reg.rebind("NomService", obj);
    }
}