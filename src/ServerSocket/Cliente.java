package ServerSocket.Ejer1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8080);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String mensaje = in.readLine();
        System.out.println("Mensaje del servidor: " + mensaje);
        in.read();
        socket.close();
    }
}
