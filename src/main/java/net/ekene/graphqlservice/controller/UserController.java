package net.ekene.graphqlservice.controller;

import lombok.RequiredArgsConstructor;
import net.ekene.graphqlservice.model.User;
import net.ekene.graphqlservice.resolver.MutationResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final MutationResolver mutationResolver;

    User addUser(@Argument User user){
        return mutationResolver.signUp(user.getUsername(), user.getPassword());
    }
}
