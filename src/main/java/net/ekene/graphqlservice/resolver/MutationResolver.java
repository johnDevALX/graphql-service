package net.ekene.graphqlservice.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import net.ekene.graphqlservice.model.User;
import net.ekene.graphqlservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        User existingUser = userRepository.findByUsername(username).get();
        if (existingUser != null) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        return userRepository.save(newUser);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).get();
        if (user.getPassword().equals(password)){
            return "You're logged in";
        } else {
            return  "Invalid Password or username provided";
        }
    }
}

