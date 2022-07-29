package tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tutorial.model.User;
import tutorial.repository.UserRepository;


// http://localhost:8080/api/users
@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.save(new User("SpongeBob","spongebob@gmail.com"));
        this.userRepository.save(new User("Patrick","patrick@gmail.com"));
        this.userRepository.save(new User("Betsy","betsy@gmail.com"));


    }
}

