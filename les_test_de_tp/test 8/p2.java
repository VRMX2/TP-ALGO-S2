import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p2 extends UnicastRemoteObject implements MonInterface {
    protected p2() throws RemoteException {
        super();
    }

    @Override
    public void maMethode1(int N, int S2) throws RemoteException {
       try {
           Registry registry = LocateRegistry.getRegistry("localhost", 1099);
           MonInterface stub = (MonInterface) registry.lookup("NomService");
           stub.maMethode1(N, S2);
       } catch (Exception e) {
        e.printStackTrace();
       }
    }

    @Override
    public void maMethode2(int S) throws RemoteException {
        try {
            Socket socket = new Socket("localhost", 4000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeInt(S);
            oos.flush(); 
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1111);
        p2 obj = new p2();
        Registry reg = LocateRegistry.getRegistry("localhost", 1111);
        reg.rebind("NomService", obj);

        try{
            ServerSocket serverSocket1 = new ServerSocket(1000);
            while (true) {
                Socket socket1 = serverSocket1.accept();
                ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
                int N = ois.readInt();
                socket1.close();

                int S2 = N * 2;
                System.out.println(S2);

                obj.maMethode1(N, S2);
            }
                
            

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
