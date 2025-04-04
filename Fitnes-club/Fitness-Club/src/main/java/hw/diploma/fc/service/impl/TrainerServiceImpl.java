package hw.diploma.fc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hw.diploma.fc.dto.Trainer;
import hw.diploma.fc.dto.TrainerCreateDto;
import hw.diploma.fc.dto.TrainerUpdateDto;
import hw.diploma.fc.service.TrainerService;
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
public class TrainerServiceImpl implements TrainerService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;


    private final String BASE_TRAINER_URL = "http://localhost:8080/api/trainer";

    @PreAuthorize("hasRole('USER')")
    public List<Trainer> getTrainers() {

        ResponseEntity<String> response = null;
        List<Trainer> trainers = null;
        try {
            response = restTemplate.getForEntity(BASE_TRAINER_URL, String.class);
            trainers = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getTrainers(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getTrainers(): error when sending get trainer request");
            throw new RuntimeException();
        }
        return trainers;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Trainer getTrainerById(Long id) {

        ResponseEntity<String> response = null;
        Trainer trainer = null;
        try {
            response = restTemplate.getForEntity(BASE_TRAINER_URL + "/" + id, String.class);
            trainer = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getTrainerById(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getTrainerById(): error when sending get trainer request");
            throw new RuntimeException();
        }
        return trainer;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Trainer createTrainer(TrainerCreateDto createTrainer) {

        String response = null;
        Trainer trainer = null;
        try {
            response = restTemplate
                    .postForObject(BASE_TRAINER_URL, createTrainer, String.class);
            trainer = objectMapper.readValue(response, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("createTrainer(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("createTrainer(): error when sending post trainer request");
            throw new RuntimeException();
        }
        return trainer;

    }

    @PreAuthorize("hasRole('ADMIN')")
    public Trainer updateTrainer(Long id, TrainerUpdateDto updateTrainer) {

        String response = null;
        Trainer trainer = null;
        try {
            response = restTemplate
                    .patchForObject(BASE_TRAINER_URL + "/" + id, updateTrainer, String.class);
            trainer = objectMapper.readValue(response, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("updateTrainer(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("updateTrainer(): error when sending patch trainer request");
            throw new RuntimeException();
        }
        return trainer;
    }

    @PreAuthorize("hasRole('OWNER')")
    public void deleteTrainer(Long id) {
        try {
            restTemplate.delete(BASE_TRAINER_URL + "/" + id);
        } catch (RestClientException e) {
            log.error("deleteTrainer(): error when sending delete trainer request");
            throw new RuntimeException();
        }
    }

}
