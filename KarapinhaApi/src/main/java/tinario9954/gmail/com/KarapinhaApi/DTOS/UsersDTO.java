package tinario9954.gmail.com.KarapinhaApi.DTOS;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import tinario9954.gmail.com.KarapinhaApi.Models.Users;

@Getter
@Setter
public class UsersDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String image;
    private String usernames;

    // Construtor vazio
    public UsersDTO() {
    }

    // Construtor que converte a entidade Users em um DTO
    public UsersDTO(Users user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.telephone = user.getTelephone();
        this.image = user.getImage();
        this.usernames = user.getUsername(); // ou user.getUsernames() se a intenção for outro campo.
    }

    // Construtor com campos para criar um novo DTO diretamente
    public UsersDTO(Long id, String firstName, String lastName, String email, String telephone, String image,
            String usernames) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.image = image;
        this.usernames = usernames;
    }
}
