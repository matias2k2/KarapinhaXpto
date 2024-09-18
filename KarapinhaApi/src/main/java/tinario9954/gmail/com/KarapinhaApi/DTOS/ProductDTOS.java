package tinario9954.gmail.com.KarapinhaApi.DTOS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tinario9954.gmail.com.KarapinhaApi.Models.Category;
import tinario9954.gmail.com.KarapinhaApi.Models.Product;

@Getter
@Setter
public class ProductDTOS implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracters")
    @NotBlank(message = "Campo obrigatorio")
    private String name;
   
    private String description;
    @Positive(message = "preco deve ser um valor possitivo")
    private Double price;
    private String img;

    private List<categoryDTO> _CategoryDTOS = new ArrayList<>(); 

    public ProductDTOS()
    {}

    public ProductDTOS(Long id, String name, String description, Double price, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }
    
    public ProductDTOS(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.img = entity.getImg();
    }
    public ProductDTOS(Product entity, Set<Category> _categorias)
    {
        this(entity);
        _categorias.forEach(x->this._CategoryDTOS.add(new categoryDTO(x)));
    }
    


}
