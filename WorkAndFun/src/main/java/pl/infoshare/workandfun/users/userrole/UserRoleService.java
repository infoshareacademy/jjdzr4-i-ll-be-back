package pl.infoshare.workandfun.users.userrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole findByName(String name) {
        Optional<UserRole> foundUserRole = userRoleRepository.findByName(name);
        return foundUserRole.orElseThrow(() -> {
            throw new RuntimeException("Cannot fin UserRole with name:" + name);
            //FIXME: implement new exception UserRoleNotFoundException(name)
        });
    }
}