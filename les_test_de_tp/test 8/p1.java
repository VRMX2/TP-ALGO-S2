import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class p1{
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            ServerSocket serverSocket1 = new ServerSocket(4000);
            while (true) {
                System.out.println("entrer la valeur de N:");
                int N = scanner.nextInt();

                Socket socket = new Socket("localhost", 1000);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(N);
                oos.flush(); 
                socket.close();

                Socket socket1 = serverSocket1.accept(); 
                ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
                int S = ois.readInt();
                System.out.println(S);
                socket1.close(); 

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}