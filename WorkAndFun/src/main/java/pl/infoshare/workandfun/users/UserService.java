package pl.infoshare.workandfun.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.users.mappers.UserToUserDtosMapper;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserToUserDtosMapper userToUserDtosMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserToUserDtosMapper userToUserDtosMapper) {
        this.userRepository = userRepository;
        this.userToUserDtosMapper = userToUserDtosMapper;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFound = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User: " + username + " does not exist"));
        return userToUserDtosMapper.toSecurityDto(userFound);
    }
}
