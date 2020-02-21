package ru.ilnik.garage.config;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ilnik.garage.util.exception.graphql.CustomGraphQLErrorHandler;
import ru.ilnik.garage.util.exception.graphql.GraphQLErrorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new CustomGraphQLErrorHandler()
        {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }
}
