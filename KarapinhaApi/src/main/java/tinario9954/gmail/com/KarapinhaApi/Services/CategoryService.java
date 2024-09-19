package tinario9954.gmail.com.KarapinhaApi.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import tinario9954.gmail.com.KarapinhaApi.DTOS.categoryDTO;
import tinario9954.gmail.com.KarapinhaApi.Models.Category;
import tinario9954.gmail.com.KarapinhaApi.Repository.CategoryRepository;



@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository _categoryRepository;
    @Transactional
    public Page<categoryDTO> findAllPaged(PageRequest pageRequest) {

        Page<Category> resulte = _categoryRepository.findAll(pageRequest);
        return resulte.map(x -> new categoryDTO(x));
    }

    // findById

    @Transactional
    public categoryDTO findById(Long id) {
        Optional<Category> obj = _categoryRepository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new categoryDTO(entity);
    }

    @Transactional
    public categoryDTO insert(categoryDTO entity) {
        Category _entity = new Category();
        _entity.setId(entity.getId());
        _entity.setName(entity.getName());
        _entity = _categoryRepository.save(_entity);
        return new categoryDTO(_entity);
    }

    public categoryDTO update(categoryDTO entity, Long id) {
        try {
            // atualizar os dados
            Category _entity = _categoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria nao Encontrada "));
            _entity.setId(entity.getId());
            _entity.setName(entity.getName());
            _entity = _categoryRepository.save(_entity);
            return new categoryDTO(_entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        }

    }

    public void delectar(Long id) {
        try {
            _categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }
    }
    
}
