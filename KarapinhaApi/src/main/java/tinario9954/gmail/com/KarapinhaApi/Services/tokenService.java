package tinario9954.gmail.com.KarapinhaApi.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import tinario9954.gmail.com.KarapinhaApi.Models.Users;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service; // Importando a anotação correta

@Service // Anotação correta para marcar a classe como um Bean
public class tokenService {

    public String gerarToken(Users user) {
        Instant expirationTime = LocalDateTime.now().plusMinutes(10)
                .atZone(ZoneId.of("-03:00")).toInstant();

        return JWT.create()
                .withIssuer("Produtos")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(Date.from(expirationTime))
                .sign(Algorithm.HMAC256("matiasG")); // Chave secreta usada para assinar o token
    }
}
