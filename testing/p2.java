import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
    public void maMethode1(int N,int S2) throws RemoteException {}
 @Override public void maMethode2(int s) throws RemoteException {
    try{
        Socket socket = new Socket("localhost", 4000);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeInt(s);
        oos.flush(); 
        socket.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
 }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1111);
        p2 obj = new p2();
        Registry reg = LocateRegistry.getRegistry("localhost", 1111);
        reg.rebind("NomService", obj);

        try{
            DatagramSocket udpSocket = new DatagramSocket(1000);
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet); 
                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.trim().split(",");
                int N = Integer.parseInt(parts[0]);

                int S2 = N*2;
                System.out.println(S2);

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MonInterface stub = (MonInterface) registry.lookup("NomService");
                stub.maMethode1(N,S2);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}