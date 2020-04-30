package ru.ilnik.garage.util.exception.graphql;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    public CustomGraphQLErrorHandler() {
    }

    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        List<GraphQLError> clientErrors = this.filterGraphQLErrors(errors);
        List<GraphQLError> internalErrors = errors.stream()
                .filter(this::isInternalError)
                .map(GraphQLErrorAdapter::new)
                .collect(Collectors.toList());

        if (clientErrors.size() + internalErrors.size() < errors.size()) {
            clientErrors.add(new GenericGraphQLError("Internal Server Error(s) while executing query"));
            errors.stream()
                    .filter((error) -> !this.isClientError(error))
                    .forEach((error) -> {
                        if (error instanceof Throwable) {
                            log.error("Error executing query!", (Throwable) error);
                        } else {
                            log.error("Error executing query ({}): {}", error.getClass().getSimpleName(), error.getMessage());
                        }

                    });
        }
        List<GraphQLError> finalErrors = new ArrayList<>();
        finalErrors.addAll(clientErrors);
        finalErrors.addAll(internalErrors);

        return finalErrors;
    }

    protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
        return errors.stream().filter(this::isClientError).collect(Collectors.toList());
    }

    protected boolean isClientError(GraphQLError error) {
        return !(error instanceof ExceptionWhileDataFetching) && !(error instanceof Throwable);
    }

    protected boolean isInternalError(GraphQLError error) {
        return (error instanceof ExceptionWhileDataFetching) &&
                (((ExceptionWhileDataFetching) error).getException() instanceof CustomGraphQLException);
    }
}
