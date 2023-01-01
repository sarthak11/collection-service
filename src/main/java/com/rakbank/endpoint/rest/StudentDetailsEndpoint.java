package com.rakbank.endpoint.rest;

import com.rakbank.client.RestApiClient;
import com.rakbank.dto.StudentResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class StudentDetailsEndpoint extends BaseEndpoint implements BaseEndpointInterface {

    public StudentDetailsEndpoint(RestApiClient restApiClient, String url, Map<String, String> headersMap, Map<String, String> uriVariablesMap) {
        super(restApiClient, url, headersMap, uriVariablesMap);
    }

    @Override
    public StudentResponse execute() {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url).toUriString();
        return restApiClient.makeGetCall(urlTemplate, headersMap, uriVariablesMap, StudentResponse.class);
    }

}
