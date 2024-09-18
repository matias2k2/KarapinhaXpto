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
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tinario9954.gmail.com.KarapinhaApi.DTOS.RoleDTOS;
import tinario9954.gmail.com.KarapinhaApi.DTOS.UserInsertDTO;
import tinario9954.gmail.com.KarapinhaApi.DTOS.UsersDTO;
import tinario9954.gmail.com.KarapinhaApi.Models.Role;
import tinario9954.gmail.com.KarapinhaApi.Models.Users;
import tinario9954.gmail.com.KarapinhaApi.Repository.RoleRepository;
import tinario9954.gmail.com.KarapinhaApi.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RoleRepository _roleRepository;
    
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public Page<UsersDTO> findAllPaged(PageRequest pageRequest) {
        Page<Users> resulte = _userRepository.findAll(pageRequest);
        return resulte.map(x -> new UsersDTO(x));
    }

    @Transactional
    public UsersDTO findById(Long id) {
        Optional<Users> obj = _userRepository.findById(id);
        Users entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new UsersDTO(entity);
    }

    public UsersDTO insert(UserInsertDTO entity) {
        Users _entity = new Users();
        copyDtoEntity(entity, _entity);
        if (entity.getPassword() != null && !entity.getPassword().isEmpty()) {
            _entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        } else {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        _entity = _userRepository.save(_entity);
        return new UsersDTO(_entity);
    }

    public UsersDTO update(UsersDTO entity, Long id) {
        try {
            Users _entity = _userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Produto/os nao Encontrada "));
            copyDtoEntity(entity, _entity);
            _entity = _userRepository.save(_entity);
            return new UsersDTO(_entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void delectar(Long id) {
        try {
            _userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }
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
        } else {
            throw new IllegalArgumentException("Password cannot be null");
        }
        _entity.getRoles().clear();
        for (RoleDTOS roleDTOS : entity.getRolesDTO()) {
            Role roles = _roleRepository.getReferenceById(roleDTOS.getId());
            _entity.getRoles().add(roles);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Users users = _userRepository.findByEmail(username);
        if (users == null) {
            logger.error("User not found" + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found " + username);
        return users;
    }

}
