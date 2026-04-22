import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class p1 extends Thread {

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(4000);
                Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.print("Enter N: ");
                int n = scanner.nextInt();

                try {
                    Socket socket = new Socket("localhost", 2000);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeInt(n);
                    oos.flush();
                    socket.close();
                } catch (Exception e) {
                    System.out.println("p2 not ready: " + e.getMessage());
                    continue; 
                }

                System.out.println("Waiting for s3...");
                Socket socketP4 = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socketP4.getInputStream());
                int s3 = ois.readInt();
                System.out.println("FINAL RESULT s3 = " + s3);
                socketP4.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}