import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MonInterface extends Remote {
    void maMethode1(int N,int S2) throws RemoteException; // throws obligatoire
    void maMethode2(int S) throws RemoteException; // throws obligatoire
}
