package ru.ilnik.garage.security;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import ru.ilnik.garage.model.User;

@Component
public class JwtGenerator {
//    public String generate(User user) {
//
//
//        Claims claims = Jwts.claims().setSubject(user.getEmail());
//        claims.put("password", String.valueOf(user.getPassword()));
//        claims.put("roles", user.getRoles());
//
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, "Graphql")
//                .compact();
//    }
}
