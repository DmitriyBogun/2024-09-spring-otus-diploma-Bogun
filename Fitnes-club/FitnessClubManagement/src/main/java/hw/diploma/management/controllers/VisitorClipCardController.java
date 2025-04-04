package hw.diploma.management.controllers;

import hw.diploma.management.dto.VisitorClipCardCreateDto;
import hw.diploma.management.dto.VisitorClipCardDto;
import hw.diploma.management.dto.VisitorClipCardUpdateDto;
import hw.diploma.management.services.VisitorClipCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/card")
public class VisitorClipCardController {

    private final VisitorClipCardService visitorClipCardService;

    @GetMapping("/{id}")
    public VisitorClipCardDto getClipCardById(@PathVariable(value = "id", required = false) Long id){
        log.info("getClipCardById(): id {}", id);
        return visitorClipCardService.getClipCardById(id);
    }

    @GetMapping
    public List<VisitorClipCardDto> getAllCards(){
        log.info("getAllCards()");
        return visitorClipCardService.findAllCard();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitorClipCardDto createClipCard(@Valid @RequestBody VisitorClipCardCreateDto visitorClipCardCreateDto){
        log.info("createClipCard(): dto {}", visitorClipCardCreateDto);
        return visitorClipCardService.create(visitorClipCardCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public VisitorClipCardDto updateClipCardById(@PathVariable(value = "id") Long id,
                                                       @Valid @RequestBody VisitorClipCardUpdateDto visitorClipCardUpdateDto){
        log.info("updateClipCardById(): dto {}", visitorClipCardUpdateDto);
        visitorClipCardUpdateDto.setId(id);
        return visitorClipCardService.update(visitorClipCardUpdateDto);
    }

    @PatchMapping("/workout/{id}")
    public VisitorClipCardDto subtractWorkoutFromCard(@PathVariable("id") Long id){
        log.info("subtractWorkoutFromCard(): id {}", id);
        return visitorClipCardService.subtractWorkout(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClipCardById(@PathVariable(value = "id") Long id){
        log.info("deleteClipCardById(): id {}", id);
        visitorClipCardService.deleteClipCardById(id);
    }
}
