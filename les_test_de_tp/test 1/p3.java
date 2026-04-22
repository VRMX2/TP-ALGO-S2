import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p3 extends UnicastRemoteObject implements MonInterface {
    protected p3() throws RemoteException {
        super();
    }

 @Override public void maMethode(int N, int S2) throws RemoteException {
    int S3  = (S2*3) + N;
    System.out.println("S3 = "+S3);

    try {
        Registry registry = LocateRegistry.getRegistry("localhost", 1111);
        MonInterface1 stub = (MonInterface1) registry.lookup("NomService");
        stub.maMethode1(S3);
    } catch (Exception e) {
        e.printStackTrace();
    }
 }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        p3 obj = new p3();
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        reg.rebind("NomService", obj);
    }
}
