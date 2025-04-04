package hw.diploma.management.controllers;

import hw.diploma.management.dto.TrainerCreateDto;
import hw.diploma.management.dto.TrainerDto;
import hw.diploma.management.dto.TrainerUpdateDto;
import hw.diploma.management.services.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping
    public List<TrainerDto> getAllTrainers(){
        log.info("getAllTrainers()");
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{id}")
    public TrainerDto getTrainerById(@PathVariable(value = "id", required = false) Long id){
        log.info("getTrainerById(): id {}", id);
        return trainerService.getTrainerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerDto createTrainer(@Valid @RequestBody TrainerCreateDto trainerCreateDto){
        log.info("createTrainer(): dto {}", trainerCreateDto);
        return trainerService.create(trainerCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TrainerDto updateTrainer(@PathVariable(value = "id") Long id,
            @Valid @RequestBody TrainerUpdateDto trainerUpdateDto){
        log.info("updateTrainer(): dto {}", trainerUpdateDto);
        trainerUpdateDto.setId(id);
        return trainerService.update(trainerUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainerById(@PathVariable(value = "id") Long id){
        log.info("deleteTrainerById(): id {}", id);
        trainerService.deleteById(id);
    }

}
