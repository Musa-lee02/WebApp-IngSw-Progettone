package pattern.skillmatchbackend.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenManager {

    private static TokenManager instance;

    private TokenManager() {
    }

    public static TokenManager getInstance() {
        if (instance == null)
            return new TokenManager();
        return instance;
    }


    private static final String SECRET_KEY = "SKILLMATCHBACKEND2024T569865V86586BTRIE53DXFSYLJHGopUYRX766K87TRC5BWEZ43QA8UJH0986RT63MJ7GV7NTDE";

    public static String creaToken(String username, Integer expirationTimeInMilliSeconds) {

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeInMilliSeconds); // Token valido per 2 giorni


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


