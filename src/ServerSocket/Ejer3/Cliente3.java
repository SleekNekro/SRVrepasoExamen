package ServerSocket.Ejer3;

import java.io.*;
import java.net.*;

public class Cliente3 {
    public static void main(String[] args) {
        try {
            // Conectar al servidor en el puerto 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado al servidor.");

            // Streams para leer y escribir datos
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String mensaje;
            while (true) {
                // Leer mensaje desde el teclado
                System.out.print("Tú: ");
                mensaje = userInput.readLine();

                // Enviar mensaje al servidor
                writer.println(mensaje);

                // Si el mensaje es "/exit", cerramos la conexión
                if (mensaje.equalsIgnoreCase("/exit")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }

                // Leer la respuesta del servidor
                String respuesta = reader.readLine();
                System.out.println(respuesta);
            }

            // Cerrar la conexión
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
