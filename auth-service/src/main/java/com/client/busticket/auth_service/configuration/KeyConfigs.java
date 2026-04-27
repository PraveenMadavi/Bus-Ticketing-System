package com.client.busticket.auth_service.configuration;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class KeyConfigs {

    private final String keyId;

    public KeyConfigs(@Value("${jwt.key-id}") String keyId) {
        this.keyId = keyId;
    }

    private String readKey(Resource resource) throws Exception {
        try (InputStream is = resource.getInputStream()) {
            String key = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return key.replaceAll("-----\\w+ PRIVATE KEY-----", "")
                    .replaceAll("-----\\w+ PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
        }
    }

    @Bean
    public RSAPrivateKey privateKey() throws Exception {
        Resource resource = new ClassPathResource("keys/private.pem");
        String key = readKey(resource);

        byte[] decoded = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);

        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    @Bean
    public RSAPublicKey publicKey() throws Exception {
        Resource resource = new ClassPathResource("keys/public.pem");
        String key = readKey(resource);

        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);

        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    @Bean
    public RSAKey rsaPublicKey(RSAPublicKey publicKey) {
        return new RSAKey.Builder(publicKey)
                .keyID(keyId)
                .build();
    }

    @Bean
    public JWKSet jwkSet(RSAKey rsaPublicKey) {
        return new JWKSet(rsaPublicKey);
    }
}
