package tinario9954.gmail.com.KarapinhaApi.DTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String token;
    private long expiresIn;

    // Setters que retornam o próprio objeto LoginResponse
    public LoginResponse setToken(String token) {
        this.token = token;
        return this; // Retorna o próprio objeto
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this; // Retorna o próprio objeto
    }

    // Getters para token e expiresIn
    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
