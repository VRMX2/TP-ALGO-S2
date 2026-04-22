import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class p3 extends UnicastRemoteObject implements MonInterface {
    protected p3() throws RemoteException {
        super();
    }

 @Override public void maMethode1(int N,int S2) throws RemoteException {
    int S3 = S2 * N;
    System.out.println(S3);
    
    try {
        DatagramSocket udpSocket = new DatagramSocket();
            String msg = N+","+S2+","+S3;
            byte[] buffer = msg.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 2000);
            udpSocket.send(packet);


    } catch (Exception e) {
        e.printStackTrace();
    }
 }

 @Override public void maMethode2(int S) throws RemoteException{
     try{
    Registry registry = LocateRegistry.getRegistry("localhost", 1111);
     MonInterface stub = (MonInterface) registry.lookup("NomService");
     stub.maMethode2(S);
     }
     catch(Exception e){
        e.printStackTrace();
     }
 }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        p3 obj = new p3();
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        reg.rebind("NomService", obj);

        try{
            DatagramSocket udpSocket1 = new DatagramSocket(3000); 
            while (true) {
                byte[] buffer1 = new byte[1024];
                DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);
                udpSocket1.receive(packet1); 
                String msg = new String(packet1.getData(), 0, packet1.getLength());
                String[] parts = msg.trim().split(","); // découper
                int S = Integer.parseInt(parts[0]);

                obj.maMethode2(S);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
