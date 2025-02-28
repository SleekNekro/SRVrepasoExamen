package Cripto;

import javax.crypto.*;
import java.io.*;
import java.util.Base64;

public class Ejer5 {
    public static void main(String[] args) throws Exception {
        String archivoOriginal = "archivo.txt";
        String archivoCifrado = "archivo_cifrado.txt";
        String archivoDescifrado = "archivo_descifrado.txt";

        SecretKey key = generarClaveSecreta();

        // Cifrar archivo
        cifrarArchivo(archivoOriginal, archivoCifrado, key);
        System.out.println("Archivo cifrado guardado en: " + archivoCifrado);

        // Descifrar archivo
        descifrarArchivo(archivoCifrado, archivoDescifrado, key);
        System.out.println("Archivo descifrado guardado en: " + archivoDescifrado);
    }

    // Generar clave secreta AES
    public static SecretKey generarClaveSecreta() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Clave de 128 bits
        return keyGen.generateKey();
    }

    // Cifrar archivo
    public static void cifrarArchivo(String archivoOriginal, String archivoCifrado, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

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

    // Descifrar archivo
    public static void descifrarArchivo(String archivoCifrado, String archivoDescifrado, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

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
