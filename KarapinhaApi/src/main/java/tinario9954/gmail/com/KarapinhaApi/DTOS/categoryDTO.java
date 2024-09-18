package tinario9954.gmail.com.KarapinhaApi.DTOS;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tinario9954.gmail.com.KarapinhaApi.Models.Category;
@Getter
@Setter

public class categoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracters ")
    @NotBlank(message = "campo obrigatorio")
    private String name;

    public categoryDTO() {
    }

    public categoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
