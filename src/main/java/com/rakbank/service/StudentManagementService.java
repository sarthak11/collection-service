package com.rakbank.service;

import com.rakbank.client.RestApiClient;
import com.rakbank.dto.StudentResponse;
import com.rakbank.endpoint.rest.StudentDetailsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StudentManagementService {

    @Autowired
    private RestApiClient restApiClient;

    @Value("${student-management-service.url}")
    private String baseUrl;

    public StudentResponse getStudentDetailsById(Long studentId) {
        String endpoint = baseUrl + "/student/" + studentId;
        StudentResponse response = null;
        try {
            response = new StudentDetailsEndpoint(restApiClient, endpoint, new HashMap<>(), new HashMap<>())
                    .execute();
        } catch (Exception e) {
            return response;
        }
        return response;
    }
}
