package Cripto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class Ejer1 {

    // Usaremos un HashMap para almacenar usuarios y sus contraseñas
    private static HashMap<String, String> usuarios = new HashMap<>();

    // Sal aleatoria para añadir al hash
    private static final String SALT = "mYsAlT@123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registrarUsuario(scanner);
                    break;
                case 2:
                    iniciarSesion(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 3);
    }

    // Metodo para registrar usuario
    public static void registrarUsuario(Scanner scanner) {
        System.out.print("Ingrese el nombre de usuario: ");
        String username = scanner.nextLine();

        if (usuarios.containsKey(username)) {
            System.out.println("El usuario ya existe.");
            return;
        }

        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        // Añadimos una "sal" a la contraseña antes de hacer el hash
        String hashedPassword = hashPassword(password + SALT);

        usuarios.put(username, hashedPassword);
        System.out.println("Usuario registrado exitosamente.");
    }

    // Metodo para hacer el hash de la contraseña
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error al generar el hash.");
            return null;
        }
    }

    // Metodo para iniciar sesión
    public static void iniciarSesion(Scanner scanner) {
        System.out.print("Ingrese el nombre de usuario: ");
        String username = scanner.nextLine();

        if (!usuarios.containsKey(username)) {
            System.out.println("El usuario no existe.");
            return;
        }

        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        // Comparamos el hash de la contraseña ingresada con el almacenado
        String hashedInputPassword = hashPassword(password + SALT);
        if (hashedInputPassword.equals(usuarios.get(username))) {
            System.out.println("¡Inicio de sesión exitoso!");
            menuSesion(scanner);
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    }

    // Menú de sesión
    public static void menuSesion(Scanner scanner) {
        int opcion;

        do {
            System.out.println("Menú de usuario:");
            System.out.println("1. Mostrar usuarios y contraseñas");
            System.out.println("2. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    mostrarUsuarios();
                    break;
                case 2:
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 2);
    }

    // Metodo para mostrar usuarios
    public static void mostrarUsuarios() {
        System.out.println("Usuarios registrados:");
        for (String usuario : usuarios.keySet()) {
            System.out.println("Usuario: " + usuario);
        }
    }
}
