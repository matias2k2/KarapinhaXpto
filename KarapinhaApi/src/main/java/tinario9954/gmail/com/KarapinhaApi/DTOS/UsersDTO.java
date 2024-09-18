package tinario9954.gmail.com.KarapinhaApi.DTOS;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import tinario9954.gmail.com.KarapinhaApi.Models.Users;

@Getter
@Setter
public class UsersDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "campo obrigatório")
    private String firstName;
    @NotBlank(message = "campo obrigatório")
    private String lastName;
    @Email(message = "deve ser um email válido")
    private String email;
    @NotBlank(message = "campo obrigatório")
    private String telephone;
    private String image;
    private String usernames; // Corrigido para "username" com letra minúscula
    private String password;
    private Set<RoleDTOS> rolesDTO = new HashSet<>();

    public UsersDTO() {
    }

    public UsersDTO(Users entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.telephone = entity.getTelephone();
        this.image = entity.getImage();
        this.usernames = entity.getUsername(); 
        this.password = entity.getPassword();
        entity.getRoles().forEach(x -> this.rolesDTO.add(new RoleDTOS(x)));
    }

    public UsersDTO(Long id, String firstName, String lastName, String email, String telephone, String image,
            String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.image = image;
        this.usernames = username; // Corrigido aqui também
        this.password = password;
    }
}
