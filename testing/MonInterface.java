import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MonInterface extends Remote {
    void maMethode1(int N,int S2) throws RemoteException; 
    void maMethode2(int s) throws RemoteException; 
}
