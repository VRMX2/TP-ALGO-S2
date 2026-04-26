import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class p2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1000); 
            while (true) {
                Socket socket = serverSocket.accept(); 
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int N = ois.readInt();
                socket.close(); 

                int S2 = N*2;
                System.out.println(S2);

                Socket socket1 = new Socket("localhost", 3000);
                ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
                oos.writeInt(S2);
                oos.flush();
                socket1.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}