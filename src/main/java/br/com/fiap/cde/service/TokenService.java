package br.com.fiap.cde.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.cde.models.Credencial;
import br.com.fiap.cde.models.Token;
import br.com.fiap.cde.models.Usuario;
import br.com.fiap.cde.repository.UsuarioRepository;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    public Token generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256("S3cr3T_0");
        
        var token = JWT.create()
            .withSubject(credencial.email())
            .withIssuer("fiap-cde")
            .withExpiresAt(Instant.now().plus(29, ChronoUnit.MINUTES))
            .sign(alg);

        return new Token(token, "JWT", "Bearer");
    }

    public Usuario getUserByToken(String token) {
        Algorithm alg = Algorithm.HMAC256("S3cr3T_0");
        var email = JWT.require(alg)
                        .withIssuer("fiap-cde")
                        .build()
                        .verify(token)
                        .getSubject();
        
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new JWTVerificationException("Usuário não encontrado"));
    }
    
}
