package hw.diploma.management.services.impl;

import hw.diploma.management.dto.VisitorClipCardCreateDto;
import hw.diploma.management.dto.VisitorClipCardDto;
import hw.diploma.management.dto.VisitorClipCardUpdateDto;
import hw.diploma.management.exceptions.EntityNotFoundException;
import hw.diploma.management.mappers.VisitorClipCardMapper;
import hw.diploma.management.models.Trainer;
import hw.diploma.management.models.Visitor;
import hw.diploma.management.models.VisitorClipCard;
import hw.diploma.management.models.Workout;
import hw.diploma.management.repositories.TrainerRepository;
import hw.diploma.management.repositories.VisitorClipCardRepository;
import hw.diploma.management.repositories.VisitorRepository;
import hw.diploma.management.repositories.WorkoutRepository;
import hw.diploma.management.services.TrainerService;
import hw.diploma.management.services.VisitorClipCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisitorClipCardServiceImpl implements VisitorClipCardService {

    private final VisitorClipCardRepository visitorClipCardRepository;

    private final TrainerRepository trainerRepository;

    private final WorkoutRepository workoutRepository;

    private final VisitorRepository visitorRepository;

    private final VisitorClipCardMapper visitorClipCardMapper;

    private final TrainerService trainerService;

    @Transactional(readOnly = true)
    @Override
    public VisitorClipCardDto getClipCardById(Long id) {

        return visitorClipCardMapper.modelToDto(visitorClipCardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("VisitorCard with id = %d is not found"
                        .formatted(id))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<VisitorClipCardDto> findAllCard() {
        return visitorClipCardRepository
                .findAll()
                .stream()
                .map(visitorClipCardMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public VisitorClipCardDto create(VisitorClipCardCreateDto visitorClipCardCreateDto) {
        Trainer trainer = trainerRepository
                .findByFullName(visitorClipCardCreateDto.getTrainerName())
                .orElseThrow(() -> new EntityNotFoundException(
                "Trainer with name %s not found".formatted(visitorClipCardCreateDto.getTrainerName())));

        trainerService.addTrainersVisitorFromClipCard(trainer.getId(), visitorClipCardCreateDto.getVisitorName());

        Workout workout = workoutRepository
                .findByWorkoutName(visitorClipCardCreateDto.getWorkoutName())
                .orElseThrow(() -> new EntityNotFoundException(
                "Workout with workout name %s not found".formatted(visitorClipCardCreateDto.getWorkoutName())));

        Visitor visitor = visitorRepository.findByFullName(visitorClipCardCreateDto.getVisitorName())
                .orElseThrow(() -> new EntityNotFoundException(
                "Visitor with visitor name %s not found".formatted(visitorClipCardCreateDto.getVisitorName())));

        VisitorClipCard visitorClipCardForSave = new VisitorClipCard();
        visitorClipCardForSave.setVisitor(visitor);
        visitorClipCardForSave.setWorkoutName(workout.getWorkoutName());
        visitorClipCardForSave.setTrainerName(trainer.getFullName());
        visitorClipCardForSave.setNumberOfWorkouts(visitorClipCardCreateDto.getNumberOfWorkouts());

        return visitorClipCardMapper.modelToDto(visitorClipCardRepository.save(visitorClipCardForSave));
    }

    @Transactional
    @Override
    public VisitorClipCardDto update(VisitorClipCardUpdateDto visitorClipCardUpdateDto) {

        Trainer trainer = trainerRepository
                .findByFullName(visitorClipCardUpdateDto.getTrainerName())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Trainer with name %s not found".formatted(visitorClipCardUpdateDto.getTrainerName())));

        Workout workout = workoutRepository
                .findByWorkoutName(visitorClipCardUpdateDto.getWorkoutName())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Workout with workout name %s not found".formatted(visitorClipCardUpdateDto.getWorkoutName())));

        Visitor visitor = visitorRepository.findByFullName(visitorClipCardUpdateDto.getVisitorName())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Visitor with visitor name %s not found".formatted(visitorClipCardUpdateDto.getVisitorName())));

        VisitorClipCard visitorClipCardForUpdate = visitorClipCardRepository.findById(visitorClipCardUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "VisitorClipCard with id %d not found".formatted(visitorClipCardUpdateDto.getId())));

        String trainerOldName = visitorClipCardForUpdate.getTrainerName();

        if(!(trainerOldName.equals(visitorClipCardUpdateDto.getTrainerName()))){
            trainerService.deleteTrainersVisitorFromClipCard(trainerOldName, visitor.getFullName());
        }
        trainerService.addTrainersVisitorFromClipCard(trainer.getId(), visitorClipCardUpdateDto.getVisitorName());

        visitorClipCardForUpdate.setVisitor(visitor);
        visitorClipCardForUpdate.setWorkoutName(workout.getWorkoutName());
        visitorClipCardForUpdate.setTrainerName(trainer.getFullName());
        visitorClipCardForUpdate.setNumberOfWorkouts(visitorClipCardUpdateDto.getNumberOfWorkouts());

        return visitorClipCardMapper.modelToDto(visitorClipCardRepository.save(visitorClipCardForUpdate));
    }

    @Override
    public VisitorClipCardDto subtractWorkout(Long id) {

        VisitorClipCard visitorClipCardForUpdate = visitorClipCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "VisitorClipCard with id %d not found".formatted(id)));
        int remainingAmountOfWorkouts = visitorClipCardForUpdate.getNumberOfWorkouts();
        if(remainingAmountOfWorkouts > 0){
            remainingAmountOfWorkouts--;
            visitorClipCardForUpdate.setNumberOfWorkouts(remainingAmountOfWorkouts);
        }
        else{
            trainerService.deleteTrainersVisitorFromClipCard(visitorClipCardForUpdate.getTrainerName(),
                    visitorClipCardForUpdate.getVisitor().getFullName());
        }

        return visitorClipCardMapper.modelToDto(visitorClipCardRepository.save(visitorClipCardForUpdate));
    }

    @Transactional
    @Override
    public void deleteClipCardById(Long id) {
        VisitorClipCard visitorClipCard = visitorClipCardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "VisitorClipCard with id %d not found".formatted(id)));
        trainerService.deleteTrainersVisitorFromClipCard(visitorClipCard.getTrainerName(),
                visitorClipCard.getVisitor().getFullName());

        visitorClipCardRepository.deleteById(id);
    }
}
