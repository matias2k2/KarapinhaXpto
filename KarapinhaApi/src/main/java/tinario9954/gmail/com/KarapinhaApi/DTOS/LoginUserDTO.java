package tinario9954.gmail.com.KarapinhaApi.DTOS;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class LoginUserDTO {
    
    private String email;
    private String passaword;
    public LoginUserDTO(){}

    public LoginUserDTO(String email, String passaword) {
        this.email = email;
        this.passaword = passaword;
    }


    

    
}
