package com.espol.model.utilidades;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptar {

// Se llama al método getInstance estático con hashing SHA, método digest () llamado para calcular el resumen del mensaje de una entrada y un array de bytes de retorno
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convierte un array de bytes en representación de signum
        BigInteger number = new BigInteger(1, hash);
        // Convierte el mensaje en un valor hexadecimal
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');// coloca ceros a la izquierda
        }
        return hexString.toString(); //retorna el string en el formato toString() antes programado
    }
}
