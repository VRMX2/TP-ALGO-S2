import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MonInterface extends Remote {
    void maMethode(int N,int S2) throws RemoteException; 
}