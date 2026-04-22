import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MonInterface extends Remote {
    void maMethode1(int N,int S2) throws RemoteException; // throws obligatoire
    void maMethode2(int S3) throws RemoteException; // throws obligatoire
    void maMethode3(int S4) throws RemoteException; // throws obligatoire
}