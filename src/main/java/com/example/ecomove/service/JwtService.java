package com.example.ecomove.service;

import com.example.ecomove.entity.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Clé secrète depuis application.properties
    // Ne jamais écrire la clé directement
    // dans le code !
    @Value("${jwt.secret}")
    private String secret;

    // Durée de validité du token : 1 heure
    private static final long
            EXPIRATION_MS = 3600000;

    // Générer un token JWT pour un utilisateur
    public String genererToken(
            Utilisateur utilisateur) {

        Key key = Keys.hmacShaKeyFor(
                secret.getBytes());

        return Jwts.builder()
                // Sujet = email de l'utilisateur
                .setSubject(utilisateur.getEmail())
                // Claims personnalisés
                .claim("userId", utilisateur.getId())
                .claim("role",
                        utilisateur.getRole().name())
                // Date d'émission
                .setIssuedAt(new Date())
                // Date d'expiration (1 heure)
                .setExpiration(new Date(
                        System.currentTimeMillis()
                                + EXPIRATION_MS))
                // Signature avec clé secrète
                .signWith(key,
                        SignatureAlgorithm.HS256)
                .compact();
    }

    // Extraire l'email depuis un token JWT
    public String extraireEmail(String token) {
        return extraireClaims(token)
                .getSubject();
    }

    // Vérifier si un token est valide
    public boolean estValide(
            String token,
            Utilisateur utilisateur) {

        String email = extraireEmail(token);
        return email.equals(
                utilisateur.getEmail())
                && !estExpire(token);
    }

    // Vérifier si un token est expiré
    private boolean estExpire(String token) {
        return extraireClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Extraire les claims d'un token
    private Claims extraireClaims(
            String token) {

        Key key = Keys.hmacShaKeyFor(
                secret.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
