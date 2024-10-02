package tinario9954.gmail.com.KarapinhaApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tinario9954.gmail.com.KarapinhaApi.DTOS.LoginUserDTO;
import tinario9954.gmail.com.KarapinhaApi.Models.Users;
import tinario9954.gmail.com.KarapinhaApi.Services.tokenService;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private tokenService tokenService; // Corrigi o nome da classe para seguir a convenção de nomenclatura Java

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDTO login) {
        try {
            // Aqui o campo "password" já está corrigido
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    login.getEmail(), login.getPassword()); // Use "getPassword" e não "getPassaword"

            // Autenticar o usuário
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            Users users = (Users) authenticate.getPrincipal();

            // Gerar o token JWT e retornar
            String token = tokenService.gerarToken(users);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha no login: " + e.getMessage());
        }
    }

}
