package ServerSocket.Ejer2;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Creamos el servidor en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(8082);
            System.out.println("Server esperando conexiones...");

            // El servidor acepta conexiones de manera repetida
            while (true) {
                // Acepta una conexión del cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                // Crear un nuevo hilo para manejar la conexión
                new Thread(new ClienteHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

// Clase para manejar cada cliente en un hilo
class ClienteHandler implements Runnable {
    private Socket clientSocket;

    public ClienteHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Creamos un flujo de salida para enviar datos al cliente
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // Enviamos el mensaje "Hola, mundo"
            out.println("Hola, mundo 2");

            // Cerramos la conexión con el cliente
            clientSocket.close();
            System.out.println("Conexión con el cliente cerrada.");
        } catch (IOException e) {
            System.out.println("Error al manejar el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
