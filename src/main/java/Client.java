import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 8081;

        String message = "";

        try (Socket clientSocket = new Socket(host, port)) {

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("now connected to " + clientSocket.getRemoteSocketAddress());

            Scanner scanner = new Scanner(System.in);

            while (!message.equals("exit")) {

                // --- FROM SERVER ---
                String resp = in.readLine();
                System.out.println("server: " + resp);

                // --- TO SERVER ---
                System.out.println("Write your message or type 'exit' for disconnect : ");
                message = scanner.nextLine();
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}