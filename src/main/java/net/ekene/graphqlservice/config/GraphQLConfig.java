package net.ekene.graphqlservice.config;


import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GraphQLConfig {

    private final GraphQLSchema graphQLSchema;


    @Bean
    public GraphQL graphQL() {
        return GraphQL.newGraphQL(graphQLSchema)
                .build();
    }
}

