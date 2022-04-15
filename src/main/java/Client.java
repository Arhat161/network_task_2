import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {


    public static void main(String[] args) {

        String host = "localhost";
        int port = 8081;


        try (
                Socket clientSocket = new Socket(host, port);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {

            System.out.println("now connected to " + clientSocket.getRemoteSocketAddress());

            String clientNameResponse = in.readLine();
            System.out.println("server : " + clientNameResponse);
            out.println("Sergei");
            System.out.println("client : Sergei");

            String isChildAnswerResponse = in.readLine();
            System.out.println("server : " + isChildAnswerResponse);
            out.println("yes");
            System.out.println("client : yes");

            String welcomeMessage = in.readLine();
            System.out.println("server : " + welcomeMessage);

            String nameAndPortAnswer = in.readLine();
            System.out.println("server : " + nameAndPortAnswer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
