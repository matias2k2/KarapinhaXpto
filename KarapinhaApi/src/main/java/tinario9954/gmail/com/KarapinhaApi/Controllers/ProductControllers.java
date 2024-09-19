package tinario9954.gmail.com.KarapinhaApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tinario9954.gmail.com.KarapinhaApi.DTOS.ProductDTOS;
import tinario9954.gmail.com.KarapinhaApi.Services.ProductService;


@RestController
@RequestMapping(value = "/Products")
public class ProductControllers {
    
    @Autowired
    private ProductService _productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTOS>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy

    ) {
        PageRequest _pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<ProductDTOS> lista = _productService.findAllPaged(_pageRequest);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTOS> findById(@PathVariable Long id) {
        ProductDTOS resultado = _productService.findById(id);
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping
    public ResponseEntity<ProductDTOS> insert(@Valid @RequestBody ProductDTOS entity) {
        ProductDTOS _entity = _productService.insert(entity);
        return ResponseEntity.ok().body(_entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTOS> update(@PathVariable Long id, @Valid @RequestBody ProductDTOS dto) {
        ProductDTOS _entity = _productService.update(dto, id);
        return ResponseEntity.ok().body(_entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTOS> delectar(@PathVariable Long id) {
        _productService.delectar(id);
        return ResponseEntity.noContent().build();
    }
    
}
