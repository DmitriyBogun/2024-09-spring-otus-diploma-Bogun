package hw.diploma.fc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hw.diploma.fc.dto.Workout;
import hw.diploma.fc.dto.WorkoutCreateDto;
import hw.diploma.fc.service.WorkoutService;
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
public class WorkoutServiceImpl implements WorkoutService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String BASE_WORKOUT_URL = "http://clubmanagement:8080/api/workout";

    @PreAuthorize("hasRole('USER')")
    @Override
    public List<Workout> getWorkouts() {
        ResponseEntity<String> response = null;
        List<Workout> workouts = null;
        try {
            restTemplate.getForEntity(BASE_WORKOUT_URL, String.class);
            workouts = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (
                JsonProcessingException e) {
            log.error("getWorkouts(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getWorkouts(): error when sending get workouts request");
            throw new RuntimeException();
        }
        return workouts;
    }

    @PreAuthorize("hasRole('USER')")
    public Workout getWorkout(Long id) {
        ResponseEntity<String> response = null;
        Workout card = null;
        try {
            response = restTemplate.getForEntity(BASE_WORKOUT_URL + "/" + id, String.class);
            card = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getCard(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getCard(): error when sending get workout request");
            throw new RuntimeException();
        }
        return card;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Workout createWorkout(WorkoutCreateDto createWorkout) {

        Workout card = null;
        String response = null;
        try {
            response = restTemplate
                    .postForObject(BASE_WORKOUT_URL, createWorkout, String.class);
            card = objectMapper.readValue(response, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("createCard(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("createCard(): error when sending post workout request");
        }
        return card;
    }

    @PreAuthorize("hasRole('OWNER')")
    public void deleteWorkout(Long id) {
        try {
            restTemplate.delete(BASE_WORKOUT_URL + "/" + id);
        } catch (RestClientException e) {
            log.error("deleteCard(): error when sending delete workout request");
            throw new RuntimeException();
        }
    }
}
