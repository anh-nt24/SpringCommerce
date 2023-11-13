package vn.edu.tdtu.springcommerce.utils;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class JwtUtil {

    private static final SecretKey JWT_SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

    private static byte[] getSecretKey() {
        return JWT_SECRET_KEY.getEncoded();
    }
}
