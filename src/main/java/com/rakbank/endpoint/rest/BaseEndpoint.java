package com.rakbank.endpoint.rest;

import com.rakbank.client.RestApiClient;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class BaseEndpoint {

    protected final RestApiClient restApiClient;

    protected final String url;

    protected final Map<String, String> headersMap;

    protected final Map<String, String> uriVariablesMap;
}
