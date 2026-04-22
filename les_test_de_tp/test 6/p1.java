import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class p1 {
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                System.out.println("entrer la valeur de N:");
                int N = scanner.nextInt();

                Socket socket = new Socket("localhost", 1000);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(N);
                oos.flush(); 
                socket.close();

                Socket socket1 = serverSocket.accept(); // bloque ici
                ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
                int S4 = ois.readInt();
                System.out.println(S4);
                socket1.close(); 
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
