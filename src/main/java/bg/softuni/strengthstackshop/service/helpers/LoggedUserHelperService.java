package bg.softuni.strengthstackshop.service.helpers;

import bg.softuni.strengthstackshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoggedUserHelperService {

    private final UserRepository userRepository;

    public LoggedUserHelperService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
