package com.noxfilers.fuelApp.security;

import java.util.Date;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

    private final String SECRET = "Noxfiler";
    private final String ISSUER = "Admin";
    private  String SUBJECT ;
    private final long TOKEN_VALIDITY_IN_MILLIS =   5000 *60*60L;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    public void initialize() {
        algorithm = Algorithm.HMAC256(SECRET);

        verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
    }

    public String createJWT(String number) {
        this.SUBJECT = number;
        String jwtToken = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_IN_MILLIS))
                .withJWTId(UUID.randomUUID()
                        .toString())
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .sign(algorithm);

        return jwtToken;
    }

    public DecodedJWT verifyJWT(String jwtToken) {
        try {
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            return decodedJWT;
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DecodedJWT decodedJWT(String jwtToken) {
        try {
            DecodedJWT decodedJWT = JWT.decode(jwtToken);
            return decodedJWT;
        } catch (JWTDecodeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isJWTExpired(decodedJWT(token)));
    }

    public String getUsernameFromToken(String token){

        if(decodedJWT(token) != null)
              return decodedJWT(token).getSubject();
        else return "";
    }

    public boolean isJWTExpired(DecodedJWT decodedJWT) {
        Date expiresAt = decodedJWT.getExpiresAt();
        return expiresAt.getTime() < System.currentTimeMillis();
    }

    public String getClaim(DecodedJWT decodedJWT, String claimName) {
        Claim claim = decodedJWT.getClaim(claimName);
        return claim != null ? claim.asString() : null;
    }

}