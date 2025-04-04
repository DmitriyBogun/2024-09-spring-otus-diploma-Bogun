package hw.diploma.fc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hw.diploma.fc.dto.Visitor;
import hw.diploma.fc.dto.VisitorCreateDto;
import hw.diploma.fc.service.VisitorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final String BASE_VISITOR_URL = "http://localhost:8080/api/visitor";

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<Visitor> getVisitors() {

        ResponseEntity<String> response = null;
        List<Visitor> visitors = null;
        try {
            response = restTemplate.getForEntity(BASE_VISITOR_URL, String.class);
            visitors = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getVisitors(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getVisitors(): error when sending get visitors request");
            throw new RuntimeException();
        }
        return visitors;
    }

    @PreAuthorize("hasRole('USER')")
    public Visitor getVisitorById(Long id) {

        ResponseEntity<String> response = null;
        Visitor visitor = null;
        try {
            response = restTemplate.getForEntity(BASE_VISITOR_URL + "/" + id, String.class);
            visitor = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getVisitorById(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getVisitorById(): error when sending get visitor request");
            throw new RuntimeException();
        }
        return visitor;
    }

    @PreAuthorize("hasRole('USER')")
    public Visitor createVisitor(VisitorCreateDto createVisitor) {

        String response = null;
        Visitor visitor = null;
        try {
            response = restTemplate
                    .postForObject(BASE_VISITOR_URL, createVisitor, String.class);
            visitor = objectMapper.readValue(response, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("createVisitor(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("createVisitor(): error when sending post visitor request");
            throw new RuntimeException();
        }
        return visitor;

    }

    @PreAuthorize("hasRole('OWNER')")
    public void deleteVisitor(Long id) {
        try {
            restTemplate.delete(BASE_VISITOR_URL + "/" + id);
        } catch (RestClientException e) {
            log.error("deleteVisitor(): error when sending delete visitor request");
            throw new RuntimeException();
        }
    }
}
