package by.taskManager.user_service.endpoints.handler;

import by.TaskManeger.utils.dto.TokenDTO;
import by.taskManager.user_service.config.property.JWTProperty;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenHandler {
    private JWTProperty property;

    public JwtTokenHandler(JWTProperty property) {
        this.property = property;
    }

    public String generateAccessToken(TokenDTO token) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("mail", token.getMail());
        payload.put("role",token.getRole());
        return Jwts.builder()
                .setSubject(token.getMail())
                .addClaims(payload)
                .setIssuer(property.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, property.getSecret())
                .compact();
    }

    public TokenDTO getUserToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return new TokenDTO(claims.get("mail").toString(),claims.get("role").toString());
    }


    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
