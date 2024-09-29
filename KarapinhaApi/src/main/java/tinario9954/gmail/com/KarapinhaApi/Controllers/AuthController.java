package tinario9954.gmail.com.KarapinhaApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String login(@RequestBody LoginUserDTO login) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    login.getEmail(), login.getPassaword());

            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            Users users = (Users) authenticate.getPrincipal();

            return tokenService.gerarToken(users);
        } catch (Exception e) {
            return "Falha no login! " + e.getMessage();
        }
    }
}
