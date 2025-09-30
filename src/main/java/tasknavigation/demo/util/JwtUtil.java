package tasknavigation.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "mIChAvEsEgReTa123!@#xYz456$%789AbcD"; // >=32 bytes
    private static final long EXPIRATION_MILLIS = 1000 * 60 * 60 * 24; // 24h

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String generateToken(String email) {
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        System.out.println("JwtUtil: Token gerado para email = " + email);
        return token;
    }

    public static String validateToken(String token) {
        try {
            System.out.println("JwtUtil: Validando token: " + token.substring(0, Math.min(20, token.length())) + "...");
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String subject = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date();
            System.out.println("JwtUtil: Token validado com sucesso, subject = " + subject);
            System.out.println("JwtUtil: Expira em: " + expiration + ", Agora: " + now);
            System.out.println("JwtUtil: Token válido: " + !expiration.before(now));
            return subject;
        } catch (Exception e) {
            System.out.println("JwtUtil: token inválido ou expirado! Erro: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String extractUsername(String token) {
        System.out.println("JwtUtil: Extraindo username do token...");
        return validateToken(token);
    }
    
    public static Date getExpirationDateFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration();
        } catch (Exception e) {
            System.out.println("JwtUtil: Erro ao extrair data de expiração: " + e.getMessage());
            return null;
        }
    }
}
