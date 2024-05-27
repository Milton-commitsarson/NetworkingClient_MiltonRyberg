import java.io.*;
import java.net.*;

public class NetworkingClient {
    public static void main(String[] args) {
        Socket client = null;

        int portnumber = 60000;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < 10; i++) {
            try {
                String msg = "";

                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket created " + client);

                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));


                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter Name ('Bye' to exit): ");
                msg = stdIn.readLine().trim();
                pw.println(msg);

                System.out.println("Message from server: " + br.readLine());

                pw.close();
                br.close();
                client.close();

                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }
            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
}