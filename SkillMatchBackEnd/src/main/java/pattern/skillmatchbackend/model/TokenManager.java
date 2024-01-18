package pattern.skillmatchbackend.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenManager {
    private static final String SECRET_KEY = "SKILL_MATCH_BACKEND_2024";

    public static String creaToken(String username) {

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 2 * 24 * 60 * 60 * 1000); // Token valido per 2 giorni


        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String verificaToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();

        } catch (ExpiredJwtException e) {
            return "scaduto";
        } catch (Exception e) {
            return "errore";
        }
    }




}


