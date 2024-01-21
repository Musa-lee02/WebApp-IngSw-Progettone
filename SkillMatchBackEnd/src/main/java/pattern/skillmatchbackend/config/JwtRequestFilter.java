package pattern.skillmatchbackend.config;

// path/filename: src/main/java/com/example/security/JwtRequestFilter.java

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtRequestFilter implements Filter {

    private static final String SECRET_KEY = "your_secret_key"; // Replace with your secret key

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7); // Remove "Bearer " prefix
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(jwtToken)
                        .getBody();

                // Add additional validation if necessary, like verifying user roles

            } catch (ExpiredJwtException | SignatureException e) {
                // Handle the exception, e.g., log it or set an appropriate HTTP response status
            }
        }

        chain.doFilter(request, response);
    }
}
