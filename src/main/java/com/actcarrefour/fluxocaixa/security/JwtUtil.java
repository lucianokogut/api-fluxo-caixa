package com.actcarrefour.fluxocaixa.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.actcarrefour.fluxocaixa.domain.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${auth.jwt.secret}")
    private String jwtSecret;

    @Value("${auth.jwt-expiration-milliseg}")
    private Long jwtExpirationMilliseg;

    public String gerarToken(Authentication authentication) {

        // Pegará a data atual e somará mais 8 horas em milisegundos pois assim está
        // definido nas propriedades da aplicação
        Date dataExpiracao = new Date(new Date().getTime() + jwtExpirationMilliseg);

        // Recuperará o usuário atual da autenticação forçando o ajuste via casting
        // do usuário
        Usuario usuario = (Usuario) authentication.getPrincipal();

        try {

            // Gera a chave com base na nossa secret definida nas propriedades da
            // aplicação
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));

            // Vinculará o Token com o e-mail do usuário tornando-o único
            // para os lançamentos dos títulos
            return Jwts.builder()
                    .setSubject(usuario.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(dataExpiracao)
                    .signWith(secretKey)
                    .compact();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }

    }

    // Verificará o conteúdo do Token com base na chave privada e quais as
    // permissões que o usuário teria (caso estivéssemos controlando por perfil de
    // usuário - o que não é o caso neste projeto)
    private Claims getClaims(String token) {

        try {
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Verificará o conteúdo do Token e retirará o e-mail do usuário
    public String getUserName(String token) {
        Claims claims = getClaims(token);

        if (claims == null) {
            return null;
        }

        return claims.getSubject();
    }

    // Validará o Token gerado para controle da sessão
    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);

        if (claims == null) {
            return false;
        }

        String email = claims.getSubject();
        Date dataExpiracao = claims.getExpiration();
        Date agora = new Date(System.currentTimeMillis());

        if (agora.before(dataExpiracao) && email != null) {
            return true;
        }

        return false;
    }

}
