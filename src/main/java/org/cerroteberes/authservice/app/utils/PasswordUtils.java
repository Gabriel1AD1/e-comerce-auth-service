package org.cerroteberes.authservice.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Genera el hash de una contraseña utilizando BCrypt.
     *
     * @param plainPassword La contraseña en texto plano.
     * @return El hash de la contraseña.
     */
    public static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    /**
     * Verifica si una contraseña en texto plano coincide con un hash.
     *
     * @param plainPassword  La contraseña en texto plano.
     * @param hashedPassword El hash de la contraseña.
     * @return true si coinciden, false de lo contrario.
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
