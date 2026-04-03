package ru.mtuci.coursemanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mtuci.coursemanagement.model.User;
import ru.mtuci.coursemanagement.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(
                        new User(null, "teacher",
                                passwordEncoder.encode("password"),
                                "TEACHER"));

                userRepository.save(
                        new User(null, "student",
                                passwordEncoder.encode("password"),
                                "STUDENT"));
            }
        };
    }
}
