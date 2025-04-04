package hw.diploma.fc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hw.diploma.fc.dto.*;
import hw.diploma.fc.service.VisitorCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisitorCardServiceImpl implements VisitorCardService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String BASE_CARD_URL = "http://clubmanagement:8080/api/card";

    @PreAuthorize("hasRole('OWNER')")
    @Override
    public List<VisitorClipCard> getCards() {

        ResponseEntity<String> response = null;
        List<VisitorClipCard> cards = null;
        try {
            restTemplate.getForEntity(BASE_CARD_URL, String.class);
            cards = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (
                JsonProcessingException e) {
            log.error("getCards(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getCard(): error when sending get cards request");
            throw new RuntimeException();
        }
        return cards;
    }

    @PreAuthorize("hasRole('USER')")
    public VisitorClipCard getCard(Long id) {

        ResponseEntity<String> response = null;
        VisitorClipCard card = null;
        try {
            response = restTemplate.getForEntity(BASE_CARD_URL + "/" + id, String.class);
            card = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("getCard(): can`t parse response");
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("getCard(): error when sending get card request");
            throw new RuntimeException();
        }
        return card;
    }

    @PreAuthorize("hasRole('USER')")
    public VisitorClipCard createCard(VisitorClipCardCreateDto createCard) {

        VisitorClipCard card = null;
        String response = null;
        try {
         response = restTemplate
                .patchForObject(BASE_CARD_URL, createCard, String.class);
            card = objectMapper.readValue(response, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("createCard(): can`t parse response");
            throw new RuntimeException(e);
        }catch (RestClientException e) {
            log.error("createCard(): error when sending post card request");
        }
        return card;
    }

    @PreAuthorize("hasRole('OWNER')")
    public void deleteCard(Long id) {
        try {
            restTemplate.delete(BASE_CARD_URL + "/" + id);
        } catch (RestClientException e) {
            log.error("deleteCard(): error when sending delete card request");
            throw new RuntimeException();
        }
    }

}
