package spectr.java_group.OrganizerProject.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spectr.java_group.OrganizerProject.entities.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers (){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
