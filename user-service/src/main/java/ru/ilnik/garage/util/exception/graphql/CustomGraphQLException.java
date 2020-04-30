package ru.ilnik.garage.util.exception.graphql;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomGraphQLException extends RuntimeException implements GraphQLError {
    private final String errorMessage;
    private final int errorCode;
    private final GraphQLError graphQLError;

    public CustomGraphQLException(int errorCode, String errorMessage, GraphQLError graphQLError) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.graphQLError = graphQLError;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return graphQLError.getErrorType();
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new HashMap<>();
        customAttributes.put("errorCode", this.errorCode);
        customAttributes.put("errorMessage", this.getMessage());
        return customAttributes;
    }
}
