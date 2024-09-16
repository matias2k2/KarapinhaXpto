package tinario9954.gmail.com.KarapinhaApi.DTOS;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import tinario9954.gmail.com.KarapinhaApi.Models.Users;

@Getter
@Setter
public class UsersDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String image;
    private String Username;
    private String password;
    private Set<RoleDTOS> rolesDTO = new HashSet<>();

    public UsersDTO(Long id, String firstName, String lastName, String email, String telephone, String image,
            String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.image = image;
        Username = username;
        this.password = password;
    }

    public UsersDTO(Users entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.telephone = entity.getTelephone();
        this.image = entity.getImage();
        Username = entity.getUsername();
        this.password = entity.getPassword();
        entity.getRoles().forEach(x -> this.rolesDTO.add(new RoleDTOS(x)));
    }

}
