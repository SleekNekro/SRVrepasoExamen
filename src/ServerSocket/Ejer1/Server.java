package ServerSocket.Ejer1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            if (socket != null) {}
            System.out.println("Accepted connection from " + socket.getInetAddress());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello World");
            socket.close();
            System.out.println("Socket closed");
        }
    }
}
