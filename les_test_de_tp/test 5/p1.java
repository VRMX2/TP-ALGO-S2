import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class p1 extends UnicastRemoteObject implements MonInterface {
    protected p1() throws RemoteException {
        super();
    }

    @Override
    public void maMethode1(int N,int S2) throws RemoteException {}

    @Override
    public void maMethode2(int S3) throws RemoteException {}

 @Override public void maMethode3(int S4) throws RemoteException {
    System.out.println(S4);
 }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1112);
        p1 obj = new p1();
        Registry reg = LocateRegistry.getRegistry("localhost", 1112);
        reg.rebind("NomService", obj);


            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("entrer la valeur de n:");
                int N = scanner.nextInt();

                Socket socket = new Socket("localhost", 1000);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(N);
                oos.flush(); 
                socket.close();

                Thread.sleep(200);
                

            }
    }
}