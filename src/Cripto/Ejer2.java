package Cripto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Ejer2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Generamos una clave secreta AES
            SecretKey key = generarClaveSecreta();

            // Pedir al usuario el mensaje
            System.out.print("Ingrese el mensaje a cifrar: ");
            String mensaje = scanner.nextLine();

            // Cifrar el mensaje
            String mensajeCifrado = cifrarAES(mensaje, key);
            System.out.println("Mensaje cifrado: " + mensajeCifrado);

            // Descifrar el mensaje
            String mensajeDescifrado = descifrarAES(mensajeCifrado, key);
            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo para generar una clave secreta AES
    public static SecretKey generarClaveSecreta() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);  // Tamaño de la clave AES (128 bits)
        return keyGen.generateKey();
    }

    // Metodo para cifrar un mensaje
    public static String cifrarAES(String mensaje, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Metodo para descifrar un mensaje
    public static String descifrarAES(String mensajeCifrado, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(mensajeCifrado);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted);
    }
}

