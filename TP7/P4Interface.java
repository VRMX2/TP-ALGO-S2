import java.rmi.Remote;
import java.rmi.RemoteException;

public interface P4Interface extends Remote {
    void processS2(int s2) throws RemoteException;
}