package pl.infoshare.workandfun.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void initSomeUsers() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        User user1 = new User(
//                "admin",
//                encoder.encode("SuperTajneHaslo123"),
//                Set.of(new UserRole("admin2", Collections.emptyList()), new UserRole("application_user2", Collections.emptyList())),
//                Collections.emptyList(),
//                "Kamila",
//                "Strzelecka",
//                "472384232",
//                "kamstrzel@gmail.com",
//                18,
//                Voivodeship.OPOLSKIE,
//                "Opole",
//                null);
//        User save = userRepository.save(user1);
//    }
}
