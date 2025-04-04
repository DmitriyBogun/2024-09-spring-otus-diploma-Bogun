package hw.diploma.management.services.impl;

import hw.diploma.management.dto.WorkoutCreateDto;
import hw.diploma.management.dto.WorkoutDto;
import hw.diploma.management.dto.WorkoutUpdateDto;
import hw.diploma.management.exceptions.EntityNotFoundException;
import hw.diploma.management.mappers.WorkoutMapper;
import hw.diploma.management.models.Trainer;
import hw.diploma.management.models.Workout;
import hw.diploma.management.repositories.TrainerRepository;
import hw.diploma.management.repositories.WorkoutRepository;
import hw.diploma.management.services.WorkoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    private final TrainerRepository trainerRepository;

    private final WorkoutMapper workoutMapper;

    @Transactional(readOnly = true)
    @Override
    public List<WorkoutDto> findAllWorkouts() {
        return workoutRepository.findAll()
                .stream()
                .map(workoutMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public WorkoutDto create(WorkoutCreateDto workoutCreateDto) {
        Workout workoutForSave = new Workout(null, workoutCreateDto.getWorkoutName(),
                workoutCreateDto.getCostOfWorkout(), new ArrayList<>());
        Workout savedWorkout = workoutRepository.save(workoutForSave);
        return workoutMapper.modelToDto(savedWorkout);
    }

    @Transactional
    @Override
    public WorkoutDto update(WorkoutUpdateDto workoutUpdateDto) {
        Workout workout = workoutRepository.findByWorkoutName(workoutUpdateDto.getWorkoutName())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Workout with name %s not found".formatted((workoutUpdateDto.getWorkoutName()))));
        workout.setWorkoutName(workoutUpdateDto.getWorkoutName());
        workout.setCostOfWorkout(workoutUpdateDto.getCostOfWorkout());


        Workout workoutAfterSave = workoutRepository.save(workout);
        return workoutMapper.modelToDto(workoutAfterSave);
    }

    @Transactional
    @Override
    public void deleteWorkoutById(Long id) {

        Workout workout = workoutRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Workout with id %d not found".formatted(id)));
        List<Long> trainers = workout.getTrainers().stream().map(Trainer::getId).toList();

        for (Long trainerId: trainers){
            Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(()->new EntityNotFoundException(
                    "Trainer with id %d not found".formatted((id))));
            trainer.getCompetence().remove(workout);
            trainerRepository.save(trainer);
        }
        workoutRepository.deleteById(id);
    }
}
