import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p3 extends UnicastRemoteObject implements MonInterface {
    protected p3() throws RemoteException {
        super();
    }

 @Override public void maMethode(int N, int S1) throws RemoteException {
    int S2 = (S1*3) + N;
    System.out.println(S2);

    try{
        Socket socket = new Socket("localhost", 4000);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeInt(S2);
        oos.flush();
        socket.close();

    }
    catch(Exception e){
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
