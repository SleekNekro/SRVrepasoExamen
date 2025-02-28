package Cripto;

import java.io.*;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class Ejer7 {
    public static void main(String[] args) throws Exception {
        String archivoOriginal = "archivo.txt";
        KeyPair keyPair = generarClavesRSA();

        // Generar la firma digital
        byte[] firma = generarFirma(archivoOriginal, keyPair.getPrivate());
        System.out.println("Firma generada: " + Base64.getEncoder().encodeToString(firma));

        // Verificar la firma
        boolean esValida = verificarFirma(archivoOriginal, firma, keyPair.getPublic());
        if (esValida) {
            System.out.println("La firma es válida.");
        } else {
            System.out.println("La firma no es válida.");
        }
    }

    // Generar las claves RSA
    public static KeyPair generarClavesRSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);  // Tamaño de la clave
        return keyPairGenerator.generateKeyPair();
    }

    // Generar firma digital usando RSA
    public static byte[] generarFirma(String archivo, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);

        try (FileInputStream fis = new FileInputStream(archivo)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                signature.update(buffer, 0, bytesRead);
            }
        }

        return signature.sign();
    }

    // Verificar la firma usando RSA
    public static boolean verificarFirma(String archivo, byte[] firma, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);

        try (FileInputStream fis = new FileInputStream(archivo)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                signature.update(buffer, 0, bytesRead);
            }
        }

        return signature.verify(firma);
    }
}
