package tinario9954.gmail.com.KarapinhaApi.Controllers;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import tinario9954.gmail.com.KarapinhaApi.DTOS.UserInsertDTO;
import tinario9954.gmail.com.KarapinhaApi.DTOS.UsersDTO;
import tinario9954.gmail.com.KarapinhaApi.Services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserControllers {

    @Autowired
    private UserService _userservices;

    @GetMapping
    public ResponseEntity<Page<UsersDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "firstName") String orderBy

    ) {
        PageRequest _pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<UsersDTO> lista = _userservices.findAllPaged(_pageRequest);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable Long id) {
        UsersDTO resultado = _userservices.findById(id);
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping
    public ResponseEntity<UsersDTO> insert(@Valid @RequestBody UserInsertDTO entity) {
        UsersDTO _entity = _userservices.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(_entity.getId()) // Remova toString() se getId() já for String
                .toUri(); // Converte para URI
        return ResponseEntity.created(uri).body(_entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> update(@PathVariable Long id, @Valid @RequestBody UsersDTO dto) {
        UsersDTO _entity = _userservices.update(dto, id);
        return ResponseEntity.ok().body(_entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> delectar(@PathVariable Long id) {
        _userservices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
