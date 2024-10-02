package tinario9954.gmail.com.KarapinhaApi.DTOS;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class LoginUserDTO {

    private String email;
    private String password; // Corrigido para "password"

    public LoginUserDTO() {
    }

    public LoginUserDTO(String email, String password) { // Corrigido para "password"
        this.email = email;
        this.password = password; // Corrigido para "password"
    }
}
