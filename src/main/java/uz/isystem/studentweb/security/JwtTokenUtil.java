package uz.isystem.studentweb.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import uz.isystem.studentweb.model.User;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private final String jwtSecret = "jansdjn190dsak-ndkasjnd!";
    private final String jwtIssuer = "car-sale.uz";

    public String generateToken(User user){
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId(String.valueOf(user.getId()));
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(String.format("%s %s", user.getId(), user.getPhone()));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, jwtSecret);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (12*60*60*1000)));
        jwtBuilder.setIssuer(jwtIssuer);
        return jwtBuilder.compact();
    }

    public String generateToken(Integer id, String phone){
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId(String.valueOf(id));
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(String.format("%s %s", id, phone));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, jwtSecret);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (12*60*60*1000)));
        jwtBuilder.setIssuer(jwtIssuer);
        return jwtBuilder.compact();
    }

    public String getPhone(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split(" ")[1];
    }

    public String getId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split(" ")[0];
    }

    public boolean validate(String token){
       try {
           Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }

    }
}
