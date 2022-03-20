package pl.infoshare.workandfun.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.users.dto.UserAddingDto;
import pl.infoshare.workandfun.users.mappers.UserToUserDtosMapper;
import pl.infoshare.workandfun.users.userrole.UserRoleService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private static final String SIMPLE_USER_ROLE = "APPLICATION_USER";
    private final UserRepository userRepository;
    private final UserToUserDtosMapper userToUserDtosMapper;
    private final UserRoleService userRoleService;

    @Autowired
    public UserService(UserRepository userRepository, UserToUserDtosMapper userToUserDtosMapper, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userToUserDtosMapper = userToUserDtosMapper;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFound = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User: " + username + " does not exist"));
        return userToUserDtosMapper.toSecurityDto(userFound);
    }

    public User registerNewUserAccount(UserAddingDto userAddingDto) { //throws UserAlreadyExistException
        if (usernameExist(userAddingDto.getUsername()) || emailExist(userAddingDto.getEmail())) {
            //throw new UserAlreadyExistException(...);
            //FIXME: implement new exception named UserAlreadyExistException
            return null;
        } else {
            return addSimpleUser(userAddingDto);
        }
    }

    private User addSimpleUser(UserAddingDto userAddingDto) {
        User newUser = userToUserDtosMapper.toEntity(userAddingDto);
        newUser.addRoleToSet(userRoleService.findByName(SIMPLE_USER_ROLE));
        return userRepository.save(newUser);
    }

    private boolean usernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}