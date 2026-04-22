import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class p4 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000); 
            while (true) {
                Socket socket = serverSocket.accept(); // bloque ici
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int S3 = ois.readInt();
                socket.close(); 

                int S4 = S3*4;
                System.out.println(S4);

                Socket socket1 = new Socket("localhost", 4000);
                ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
                oos.writeInt(S4);
                oos.flush(); 
                socket1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}