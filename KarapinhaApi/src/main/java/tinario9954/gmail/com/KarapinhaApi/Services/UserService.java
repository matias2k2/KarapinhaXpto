package tinario9954.gmail.com.KarapinhaApi.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tinario9954.gmail.com.KarapinhaApi.DTOS.UsersDTO;
import tinario9954.gmail.com.KarapinhaApi.Models.Users;
import tinario9954.gmail.com.KarapinhaApi.Repository.RoleRepository;
import tinario9954.gmail.com.KarapinhaApi.Repository.UserRepository;
@Service
public class UserService implements UserDetailsService{

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
