package tinario9954.gmail.com.KarapinhaApi.Services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tinario9954.gmail.com.KarapinhaApi.DTOS.UserInsertDTO;
import tinario9954.gmail.com.KarapinhaApi.DTOS.UsersDTO;

import tinario9954.gmail.com.KarapinhaApi.Models.Users;
import tinario9954.gmail.com.KarapinhaApi.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UsersDTO findById(Long id) {
        Optional<Users> obj = userRepository.findById(id);
        Users _Users = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new UsersDTO(_Users);
    }

    @Transactional
    public UsersDTO insert(UserInsertDTO entity) {
        Users _entity = new Users();
        copyDtoEntity(entity, _entity);
        _entity.setPassword(passwordEncoder.encode(entity.getPassword())); // Codifica a senha
        _entity = userRepository.save(_entity);
        return new UsersDTO(_entity);
    }

    public UsersDTO update(UsersDTO entity, Long id) {
        try {
            Users _entity = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));
            copyDtoEntity(entity, _entity);
            _entity = userRepository.save(_entity);
            return new UsersDTO(_entity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }
    }

    @Transactional
    public Page<UsersDTO> findAllPaged(PageRequest pageRequest) {
        Page<Users> resulte = userRepository.findAll(pageRequest);
        return resulte.map(x -> new UsersDTO(x));
    }

    public void copyDtoEntity(UsersDTO entity, Users _entity) {
        _entity.setId(entity.getId());
        _entity.setFirstName(entity.getFirstName());
        _entity.setLastName(entity.getLastName());
        _entity.setEmail(entity.getEmail());
        _entity.setTelephone(entity.getTelephone());
        _entity.setImage(entity.getImage());
        _entity.setUsernames(entity.getUsernames());
        if (entity.getPassword() != null) {
            _entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email);
        if (users == null) {
            logger.error("User not found: " + email);
            throw new UsernameNotFoundException("E-mail não encontrado");
        }
        logger.info("User found: " + email);
        return users; // Retornando o objeto Users, que implementa UserDetails
    }

}
