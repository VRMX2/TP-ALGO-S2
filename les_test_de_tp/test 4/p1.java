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
    public void maMethode1(int N) throws RemoteException {
    }

    @Override
    public void maMethode2(int S3) throws RemoteException {
    }

    @Override
    public void maMethode3(int S4) throws RemoteException {
        System.out.println(S4);
    }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1111);
        p1 obj = new p1();
        Registry reg = LocateRegistry.getRegistry("localhost", 1111);
        reg.rebind("NomService", obj);

        try{
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("entrer la valeur de n:");
                int N = scanner.nextInt();
                
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MonInterface stub = (MonInterface) registry.lookup("NomService");
                stub.maMethode1(N);

                Thread.sleep(200);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}