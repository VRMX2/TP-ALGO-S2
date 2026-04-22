import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class p1 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            ServerSocket serverSocket = new ServerSocket(3000);

            while (true) {
                System.out.println("Entrer la valeur de N:");
                int N = scanner.nextInt();

                // 1. Send N to p2 via TCP
                Socket socket = new Socket("localhost", 1000);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(N);
                oos.flush();
                socket.close();

                // 2. Wait for S4 from p2 via TCP
                Socket socket1 = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
                int S4 = ois.readInt();
                System.out.println(S4);
                socket1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}