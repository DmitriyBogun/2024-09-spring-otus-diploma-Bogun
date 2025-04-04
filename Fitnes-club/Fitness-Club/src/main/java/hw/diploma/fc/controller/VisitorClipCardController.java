package hw.diploma.fc.controller;

import hw.diploma.fc.dto.VisitorClipCard;
import hw.diploma.fc.dto.VisitorClipCardCreateDto;
import hw.diploma.fc.service.VisitorCardService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/fitness_club/card")
public class VisitorClipCardController {

    private final VisitorCardService cardService;

    @CircuitBreaker(name = "cardBreaker", fallbackMethod = "unknownCardFallback")
    @GetMapping("/{id}")
    VisitorClipCard getCard(@PathVariable("id") Long id){
        return cardService.getCard(id);
    }

    @CircuitBreaker(name = "cardBreaker", fallbackMethod = "unknownCardFallback")
    @GetMapping()
    List<VisitorClipCard> getCards(){
      return cardService.getCards();
    }

    @Retry(name = "retryCreateCard", fallbackMethod = "fallbackAfterRetry")
    @PostMapping()
    VisitorClipCard createCard(@RequestBody VisitorClipCardCreateDto cardCreateDto){
        return cardService.createCard(cardCreateDto);
    }

    @DeleteMapping("/{id}")
    void deleteCard(@PathVariable("id") Long id){
        cardService.deleteCard(id);
    }

    public String unknownCardFallback(Exception e) {
        log.error(e.getMessage());
        return "Босс,я устал)";
    }

    public String fallbackAfterRetry(Exception e) {
        return "Остановись, злобный разработчик,я больше не могу)";
    }
}
