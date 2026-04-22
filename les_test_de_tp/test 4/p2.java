import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
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
    public void maMethode1(int N) throws RemoteException {
        int S2 = N * 2;
        System.out.println(S2);

        try {
            DatagramSocket udpSocket = new DatagramSocket();
            String msg = N + "," + S2;
            byte[] buffer = msg.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 1000);
            udpSocket.send(packet);
            udpSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void maMethode2(int S3) throws RemoteException {
        try{
            Socket socket = new Socket("localhost", 3000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeInt(S3);
            oos.flush();
            socket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    

    @Override
    public void maMethode3(int S4) throws RemoteException{
        try{
            Registry registry = LocateRegistry.getRegistry("localhost", 1111);
            MonInterface stub = (MonInterface) registry.lookup("NomService");
            stub.maMethode3(S4);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        p2 obj = new p2();
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        reg.rebind("NomService", obj);

        new Thread(()->{
            try{
                    DatagramSocket udpSocket = new DatagramSocket(2000); // PORT obligatoire
                    while (true) {
                     byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    udpSocket.receive(packet); // bloque ici
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    String[] parts = msg.trim().split(",");
                    int S3 = Integer.parseInt(parts[0]);
                    obj.maMethode2(S3);
                    }
            
            
            }
        catch(Exception e){
            e.printStackTrace();
        }
        }).start();

        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(4000);
                while (true) {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    int S4 = ois.readInt();
                    socket.close();

                    obj.maMethode3(S4);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

           }
}