package tinario9954.gmail.com.KarapinhaApi.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays; // Certifique-se de que esta é a importação correta do pacote java.util
import tinario9954.gmail.com.KarapinhaApi.DTOS.LoginUserDTO;
import tinario9954.gmail.com.KarapinhaApi.DTOS.RegisterUserDTOS;
import tinario9954.gmail.com.KarapinhaApi.Models.Users;
import tinario9954.gmail.com.KarapinhaApi.Repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users signup(RegisterUserDTOS input) {
        // Corrigido: Usa java.util.Arrays e faz o split corretamente
        String[] names = input.getFullName().split(" ");
        String firstName = names[0];
        String lastName = names.length > 1 ? String.join(" ", Arrays.copyOfRange(names, 1, names.length)) : "";

        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public Users authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassaword()));

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + input.getEmail()));
    }
}
