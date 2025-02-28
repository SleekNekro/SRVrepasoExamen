package Cripto;

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class Ejer6 {
    public static void main(String[] args) throws Exception {
        // Generamos las claves públicas y privadas
        KeyPair keyPair = generarClavesRSA();

        String archivoOriginal = "archivo.txt";
        String archivoCifrado = "archivo_cifrado_rsa.txt";
        String archivoDescifrado = "archivo_descifrado_rsa.txt";

        // Cifrar archivo con RSA
        cifrarArchivoRSA(archivoOriginal, archivoCifrado, keyPair.getPublic());
        System.out.println("Archivo cifrado guardado en: " + archivoCifrado);

        // Descifrar archivo con RSA
        descifrarArchivoRSA(archivoCifrado, archivoDescifrado, keyPair.getPrivate());
        System.out.println("Archivo descifrado guardado en: " + archivoDescifrado);
    }

    // Generar claves RSA
    public static KeyPair generarClavesRSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);  // Tamaño de clave de 2048 bits
        return keyPairGenerator.generateKeyPair();
    }

    // Cifrar archivo usando RSA
    public static void cifrarArchivoRSA(String archivoOriginal, String archivoCifrado, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        try (FileInputStream fis = new FileInputStream(archivoOriginal);
             FileOutputStream fos = new FileOutputStream(archivoCifrado);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }

    // Descifrar archivo usando RSA
    public static void descifrarArchivoRSA(String archivoCifrado, String archivoDescifrado, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        try (FileInputStream fis = new FileInputStream(archivoCifrado);
             FileOutputStream fos = new FileOutputStream(archivoDescifrado);
             CipherInputStream cis = new CipherInputStream(fis, cipher)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
