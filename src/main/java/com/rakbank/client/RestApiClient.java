package com.rakbank.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakbank.dto.ApiErrorResponse;
import com.rakbank.exception.InternalJsonProcessingException;
import com.rakbank.exception.RestApiClientException;
import com.rakbank.exception.RestApiClientProtocolException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class RestApiClient {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    public RestApiClient(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    public <TResponse> TResponse makeGetCall(final String urlTemplate, Map<String, String> headerVariablesMap, Map<String, ?> uriVariablesMap, final Class<TResponse> responseClass) {
        try {
            return processGetRequest(urlTemplate, headerVariablesMap, uriVariablesMap, responseClass);
        } catch (ResourceAccessException ex) {
            // timeout error
            throw new RestApiClientException(ex.getMessage(), ex);
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode().isError()) {
                handleError(urlTemplate, ex);
            }
            throw new RestApiClientException(ex.getMessage(), ex);
        }
    }

    private <TResponse> TResponse processGetRequest(final String urlTemplate, Map<String, String> headerVariablesMap, Map<String, ?> uriVariablesMap, final Class<TResponse> responseClass) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        for(Map.Entry m : headerVariablesMap.entrySet()) {
            headers.set((String) m.getKey(), (String) m.getValue());
        }

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, request, String.class, uriVariablesMap);

        final TResponse result = toDto(response.getBody(), responseClass);
        return result;
    }

    private <TRequest> void handleError(final String url, final HttpStatusCodeException ex) {
        final String errorBody = ex.getResponseBodyAsString();
        try {
            final ApiErrorResponse errorResponse = mapper.readValue(errorBody, ApiErrorResponse.class);
            throw new RestApiClientProtocolException(
                    errorResponse.getMessage(),
                    errorResponse.getCode(),
                    errorResponse.getStatus()
            );
        } catch (JsonProcessingException e) {
            throw new RestApiClientException("Unable to parse error response. See: " + errorBody, e);
        }
    }

    private <T> T toDto(final String body, final Class<T> clazz) {
        try {
            return mapper.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            throw new InternalJsonProcessingException("Malformed response body for class " + clazz.getName(), e);
        }
    }

}
