package hw.diploma.fc.service;

import hw.diploma.fc.dto.Trainer;
import hw.diploma.fc.dto.TrainerCreateDto;
import hw.diploma.fc.dto.TrainerUpdateDto;

import java.util.List;

public interface TrainerService {

    List<Trainer> getTrainers();
    Trainer getTrainerById(Long id);
    Trainer updateTrainer(Long id, TrainerUpdateDto updateTrainer);
    void deleteTrainer(Long id);
    Trainer createTrainer(TrainerCreateDto createTrainer);
}
