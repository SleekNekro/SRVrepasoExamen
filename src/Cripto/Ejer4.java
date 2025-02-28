package Cripto;

import java.io.*;
import java.security.MessageDigest;
import java.util.Scanner;

public class Ejer4 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Pedimos el archivo
        System.out.print("Ingrese la ruta del archivo: ");
        String rutaArchivo = scanner.nextLine();

        // Calculamos el hash SHA-256 del archivo
        String hashCalculado = calcularHashArchivo(rutaArchivo);
        System.out.println("Hash calculado del archivo: " + hashCalculado);

        // Verificamos si el archivo ha sido alterado
        System.out.print("Ingrese el hash previamente almacenado para comparar: ");
        String hashAlmacenado = scanner.nextLine();

        if (hashCalculado.equals(hashAlmacenado)) {
            System.out.println("El archivo no ha sido alterado.");
        } else {
            System.out.println("El archivo ha sido alterado.");
        }
    }

    // Metodo para calcular el hash SHA-256 de un archivo
    public static String calcularHashArchivo(String archivo) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(archivo)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hash = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
