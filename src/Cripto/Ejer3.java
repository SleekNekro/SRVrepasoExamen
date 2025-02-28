package Cripto;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class Ejer3 {
    public static void main(String[] args) throws Exception {
        // Generamos las claves públicas y privadas RSA
        KeyPair keyPair = generarClavesRSA();

        // Mensaje a cifrar
        String mensaje = "Este es un mensaje secreto.";

        // Cifrado del mensaje
        String mensajeCifrado = cifrarRSA(mensaje, keyPair.getPublic());
        System.out.println("Mensaje cifrado: " + mensajeCifrado);

        // Descifrado del mensaje
        String mensajeDescifrado = descifrarRSA(mensajeCifrado, keyPair.getPrivate());
        System.out.println("Mensaje descifrado: " + mensajeDescifrado);
    }

    // Metodo para generar un par de claves RSA
    public static KeyPair generarClavesRSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);  // 2048 bits
        return keyPairGenerator.generateKeyPair();
    }

    // Metodo para cifrar el mensaje usando la clave pública
    public static String cifrarRSA(String mensaje, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Metodo para descifrar el mensaje usando la clave privada
    public static String descifrarRSA(String mensajeCifrado, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(mensajeCifrado);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted);
    }
}
