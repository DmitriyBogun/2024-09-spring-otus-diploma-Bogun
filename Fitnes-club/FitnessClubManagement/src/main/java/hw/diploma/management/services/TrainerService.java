package hw.diploma.management.services;

import hw.diploma.management.dto.TrainerCreateDto;
import hw.diploma.management.dto.TrainerDto;
import hw.diploma.management.dto.TrainerUpdateDto;

import java.util.List;

public interface TrainerService {

    TrainerDto getTrainerById(Long id);

    TrainerDto getTrainerByName(String fullName);

    List<TrainerDto> getAllTrainers();

    TrainerDto create(TrainerCreateDto bookDto);

    TrainerDto update(TrainerUpdateDto bookDto);

    void deleteById(Long id);

    void deleteTrainersVisitorFromClipCard(String trainerName, String  visitorName);

    void addTrainersVisitorFromClipCard(Long trainerId, String visitorName);
}
