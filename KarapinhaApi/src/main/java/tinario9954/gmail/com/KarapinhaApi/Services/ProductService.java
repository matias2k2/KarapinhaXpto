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
import tinario9954.gmail.com.KarapinhaApi.DTOS.ProductDTOS;
import tinario9954.gmail.com.KarapinhaApi.DTOS.categoryDTO;
import tinario9954.gmail.com.KarapinhaApi.Models.Category;
import tinario9954.gmail.com.KarapinhaApi.Models.Product;
import tinario9954.gmail.com.KarapinhaApi.Repository.CategoryRepository;
import tinario9954.gmail.com.KarapinhaApi.Repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository _produtoctrepositorio;

    @Autowired
    private CategoryRepository _categoryRepository;

    @Transactional
    public Page<ProductDTOS> findAllPaged(PageRequest pageRequest) {
        Page<Product> resulte = _produtoctrepositorio.findAll(pageRequest);
        return resulte.map(x -> new ProductDTOS(x));
    }

    public ProductDTOS findById(Long id)
    {
        Optional<Product> obj = _produtoctrepositorio.findById(id);
        Product entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new ProductDTOS(entity,entity.getCategorias());
    }

    public ProductDTOS insert(ProductDTOS entity)
    {
        Product _entity = new Product();
        copyDtoEntity(entity, _entity);
        _entity = _produtoctrepositorio.save(_entity);
        return new ProductDTOS(_entity);
    }

    public ProductDTOS update(ProductDTOS entity, Long id) {
        try {
            // atualizar os dados
            Product _entity = _produtoctrepositorio.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Produto/os nao Encontrada "));
            copyDtoEntity(entity, _entity);
            _entity = _produtoctrepositorio.save(_entity);
            return new ProductDTOS(_entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        }

    }

    public void delectar(Long id) {
        try {

            _produtoctrepositorio.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }
    }

    public void copyDtoEntity(ProductDTOS entity, Product _entity) {
        entity.setId(entity.getId());
        _entity.setName(entity.getName());
        _entity.setDescription(entity.getDescription());
        _entity.setPrice(entity.getPrice());
        _entity.getCategorias().clear();
        
        for (categoryDTO catDTO : entity.get_CategoryDTOS()) {
            Category _categoria = _categoryRepository.getReferenceById(catDTO.getId());
            _entity.getCategorias().add(_categoria);
        }
    }

    
}
