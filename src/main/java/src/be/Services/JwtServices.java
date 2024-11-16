package src.be.Services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;

@Service
public class JwtServices {
    @Value("${spring.env.jwt_secret}")
    private String SECRET;

    public String getJwt(String username) throws JOSEException {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .claim("password", "ai cho coi mật khẩu")
                .issuer("MK")
                .expirationTime(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .build();
        JWSSigner signer = new MACSigner(SECRET.getBytes(StandardCharsets.UTF_8));

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claimsSet);

        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public String getUsernameFromToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new MACVerifier(SECRET.getBytes(StandardCharsets.UTF_8));

        if (signedJWT.verify(verifier)) {
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            Date expirationTime = claims.getExpirationTime();

            if (expirationTime != null && new Date().after(expirationTime)) {
                throw new JOSEException("token đã hết hạn");
            }

            return claims.getSubject();
        } else {
            throw new JOSEException("Sai Token");
        }
    }

    public String validateToken(String token) throws ParseException, JOSEException, Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new MACVerifier(SECRET.getBytes(StandardCharsets.UTF_8));

        if (signedJWT.verify(verifier)) {
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            Date expirationTime = claims.getExpirationTime();

            if (expirationTime != null && new Date().after(expirationTime)) {
                return getJwt(claims.getSubject());
            }

            throw new JOSEException("Token chưa hết hạn mà refresh làm gì?");
        } else {
            throw new JOSEException("Sai Token");
        }
    }
}
