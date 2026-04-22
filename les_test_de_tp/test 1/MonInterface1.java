import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MonInterface1 extends Remote {
    void maMethode1(int S3) throws RemoteException;
}