import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MonInterface extends Remote {
    void maMethode(int S3) throws RemoteException; 
}