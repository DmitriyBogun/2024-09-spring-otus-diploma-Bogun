package hw.diploma.fc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hw.diploma.fc.dto.Report;
import hw.diploma.fc.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    private final String BASE_REPORT_URL = "http://clubmanagement:8080/api/report";

    @PreAuthorize("hasRole('OWNER')")
    public Report getReport(){
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_REPORT_URL, String.class);
        Report report = null;
        try {
            report = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getReport(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getCard(): error when sending get report request");
            throw new RuntimeException();
        }
        return report;
    }
}
