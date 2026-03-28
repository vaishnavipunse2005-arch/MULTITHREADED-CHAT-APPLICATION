import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to the chat server!");

        // Thread to listen for messages from others
        new Thread(() -> {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("\nMessage: " + msg);
                }
            } catch (IOException e) { 
                System.out.println("Server connection closed.");
            }
        }).start();

        // Main thread to send your messages
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            out.println(scanner.nextLine());
        }
    }
}