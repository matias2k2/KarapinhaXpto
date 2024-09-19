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
import tinario9954.gmail.com.KarapinhaApi.DTOS.categoryDTO;
import tinario9954.gmail.com.KarapinhaApi.Services.CategoryService;


@RestController
@RequestMapping(value = "/categories")
public class categoriControllers {
    @Autowired
    private CategoryService _categoryService;

    @GetMapping
    public ResponseEntity<Page<categoryDTO>> findAll(
         @RequestParam(value = "page", defaultValue = "0") Integer page,
         @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
         @RequestParam(value = "direction", defaultValue = "ASC") String direction,
         @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
         
    ) {
        PageRequest _pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<categoryDTO> lista = _categoryService.findAllPaged(_pageRequest); 
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<categoryDTO> findById(@PathVariable Long id) {
        categoryDTO resultado = _categoryService.findById(id);
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping
    public ResponseEntity<categoryDTO> insert(@Valid @RequestBody categoryDTO entity) {
        categoryDTO _entity = _categoryService.insert(entity);
        return ResponseEntity.ok().body(_entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<categoryDTO> update(@PathVariable Long id, @RequestBody categoryDTO dto) {
        categoryDTO _entity = _categoryService.update(dto, id);
        return ResponseEntity.ok().body(_entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<categoryDTO> delectar(@PathVariable Long id) {
        _categoryService.delectar(id);
        return ResponseEntity.noContent().build();
    }
     
}
