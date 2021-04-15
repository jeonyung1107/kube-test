package com.caffeinegorilla.kubetest.auth;

import com.caffeinegorilla.kubetest.exception.KubeException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProcessor {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProcessor.class);
    private final AuthConfig authConfig;


    public String createToken(TokenBody tokenBody){
        Map<String, Object> heaaders = new HashMap<>();
        heaaders.put("typ", "JWT");
        heaaders.put("alg", "HS256");

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytePrivateKey = Base64.getDecoder().decode(authConfig.getPrivateKey().getBytes());
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            return Jwts.builder()
                    .setHeader(heaaders)
                    .setClaims(tokenBody.toMap())
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();
        }catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new KubeException();
        }
    }

    public boolean validate(String jwt) {
        try {
            PublicKey publicKey = createPublicKey();

            Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwt);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    public TokenBody parse(String jwt){
        PublicKey publicKey = createPublicKey();

        Jws parsed = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwt);

        DefaultClaims claims = (DefaultClaims)parsed.getBody();
        final String role = (String) claims.get("role");
        final String email = (String) claims.get("email");
        final Long exp = claims.getExpiration().getTime();

        return new TokenBody(email, role, exp);
    }


    private PublicKey createPublicKey() throws KubeException{
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytePublicKey = Base64.getDecoder().decode(authConfig.getPublicKey().getBytes());
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
            return keyFactory.generatePublic(publicKeySpec);
        }catch (Exception e){
            throw new KubeException();
        }
    }
}
