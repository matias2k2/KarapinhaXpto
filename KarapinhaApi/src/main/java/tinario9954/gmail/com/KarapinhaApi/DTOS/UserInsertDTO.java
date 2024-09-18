package tinario9954.gmail.com.KarapinhaApi.DTOS;

import lombok.Getter;
import lombok.Setter;
import tinario9954.gmail.com.KarapinhaApi.security.valid.UserInsertValid;

@Getter
@Setter
@UserInsertValid
public class UserInsertDTO extends UsersDTO {
    private static final long serialVersionUID = 1L;
    private String password;

    UserInsertDTO() {
        super();
    }
}
