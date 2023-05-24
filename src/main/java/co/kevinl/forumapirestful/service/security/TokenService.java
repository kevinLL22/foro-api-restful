package co.kevinl.forumapirestful.service.security;

import co.kevinl.forumapirestful.model.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;
    public String generateToken(UserEntity user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forum api") //quien emite el jwt
                    .withSubject(user.getEmail()) //a quien va dirigido
                    .withClaim("id", user.getId()) //adding id information in token
                    .withExpiresAt(generateDateExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        System.out.println("start method get subject");
        if (token==null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            System.out.println("start try from getsubject");
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("forum api")
                    .build()
                    .verify(token);
            verifier.getSubject();
            System.out.println("end try getsubject");
        } catch (JWTVerificationException exception) {
            System.out.println(exception.getMessage());
            System.out.println("catch from creating verifier");
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("invalid verifier");
        }
        return verifier.getSubject();

    }

    private Instant generateDateExpires(){
        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-05:00"));
    }
}
