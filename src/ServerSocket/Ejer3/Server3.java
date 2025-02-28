package ServerSocket.Ejer3;

import java.io.*;
import java.net.*;

public class Server3 {
    public static void main(String[] args) {
        try {
            // Crear el ServerSocket para escuchar conexiones en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Esperando conexión...");

            // Aceptar la conexión del cliente
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            // Streams para leer y escribir datos
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String mensaje;
            while (true) {
                // Leer mensaje del cliente
                mensaje = reader.readLine();

                // Si el mensaje es "/exit", cerramos la conexión
                if (mensaje.equalsIgnoreCase("/exit")) {
                    System.out.println("Cliente ha cerrado la conexión.");
                    break;
                }

                // Respuesta automática para el mensaje especial "/help"
                if (mensaje.equalsIgnoreCase("/help")) {
                    writer.println("Comandos disponibles: \n/help - Mostrar comandos\n/exit - Cerrar sesión");
                } else {
                    // Mostrar el mensaje recibido y responderlo al cliente
                    System.out.println("Cliente: " + mensaje);
                    writer.println("Servidor: " + mensaje);
                }
            }

            // Cerrar la conexión
            reader.close();
            writer.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

