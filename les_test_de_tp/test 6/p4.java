import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p4 extends UnicastRemoteObject implements MonInterface {
    protected p4() throws RemoteException {
        super();
    }

 @Override public void maMethode(int S3) throws RemoteException {
    int S4 = S3*4;
    System.out.println(S4);

    try{
        Socket socket = new Socket("localhost", 5000);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeInt(S4);
        oos.flush(); 
        socket.close();

    }
    catch(Exception e){
        e.printStackTrace();
    }
 }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        p4 obj = new p4();
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        reg.rebind("NomService", obj);
    }
}
