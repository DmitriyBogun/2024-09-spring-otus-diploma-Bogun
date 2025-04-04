package hw.diploma.management.services.impl;

import hw.diploma.management.dto.TrainerCreateDto;
import hw.diploma.management.dto.TrainerDto;
import hw.diploma.management.dto.TrainerUpdateDto;
import hw.diploma.management.exceptions.EntityNotFoundException;
import hw.diploma.management.mappers.TrainerMapper;
import hw.diploma.management.models.Trainer;
import hw.diploma.management.models.Visitor;
import hw.diploma.management.models.Workout;
import hw.diploma.management.repositories.TrainerRepository;
import hw.diploma.management.repositories.VisitorRepository;
import hw.diploma.management.repositories.WorkoutRepository;
import hw.diploma.management.services.TrainerService;
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
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    private final WorkoutRepository workoutRepository;

    private final VisitorRepository visitorRepository;

    private final TrainerMapper trainerMapper;

    @Transactional(readOnly = true)
    @Override
    public TrainerDto getTrainerById(Long id) {

        return trainerMapper.modelToDto(trainerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Trainer with id = %d is not found"
                        .formatted(id))));
    }

    @Transactional(readOnly = true)
    @Override
    public TrainerDto getTrainerByName(String fullName) {
        return trainerMapper.modelToDto(trainerRepository.findByFullName(fullName).orElseThrow(() ->
                new EntityNotFoundException("Trainer with fullName = %s is not found"
                        .formatted(fullName))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<TrainerDto> getAllTrainers() {
        return trainerRepository.findAll()
                .stream()
                .map(trainerMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TrainerDto create(TrainerCreateDto trainerCreateDto) {

        Trainer trainerForSave = new Trainer(null,
                trainerCreateDto.getFullName(), trainerCreateDto.getAge(),
                trainerCreateDto.getCoachingExperience(), null, getWorkoutsList(trainerCreateDto.getWorkoutIds()));
        TrainerDto trainerDtoAfterSave = trainerMapper.modelToDto(trainerRepository.save(trainerForSave));
        return trainerDtoAfterSave;
    }

    @Transactional
    @Override
    public TrainerDto update(TrainerUpdateDto trainerUpdateDto) {

        Trainer trainer = trainerRepository.findById(trainerUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                "Trainer with fullName %s not found".formatted((trainerUpdateDto.getFullName()))));

        trainer.setFullName(trainerUpdateDto.getFullName());
        trainer.setAge(trainerUpdateDto.getAge());
        trainer.setCompetence(getWorkoutsList(trainerUpdateDto.getWorkoutIds()));
        trainer.setCoachingExperience(trainerUpdateDto.getCoachingExperience());
        trainer.setVisitors(getVisitorList(trainerUpdateDto.getVisitorIds()));

        Trainer trainerAfterSave = trainerRepository.save(trainer);
        return trainerMapper.modelToDto(trainerAfterSave);
    }

    @Transactional
    public void deleteTrainersVisitorFromClipCard(String name, String visitorName){
        Trainer trainer = trainerRepository.findByFullName(name)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Trainer with name %s not found".formatted(name)));
        List<Visitor> visitorList = trainer.getVisitors();
        visitorList.removeIf(visitor -> visitor.getFullName().equals(visitorName));
        trainer.setVisitors(visitorList);
        trainerRepository.save(trainer);
    }

    @Transactional
    @Override
    public void addTrainersVisitorFromClipCard(Long trainerId, String visitorName) {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Trainer with id %d not found".formatted(trainerId)));
        Visitor visitor = visitorRepository.findByFullName(visitorName)
                .orElseThrow(()->
                        new EntityNotFoundException("Visitor with name %s not found".formatted(visitorName)));
        trainer.getVisitors().add(visitor);
        trainerRepository.save(trainer);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        trainerRepository.deleteById(id);
    }

    private List<Workout> getWorkoutsList(List<Long> workoutIds){
        List<Workout> workouts = new ArrayList<>();
        if (workoutIds==null){
            return workouts;
        }
        for(Long workoutId: workoutIds){
            workouts.add(workoutRepository.findById(workoutId).orElseThrow(()->
                    new EntityNotFoundException("Workout with id %d not found".formatted(workoutId))));
        }
        return workouts;
    }

    private List<Visitor> getVisitorList(List<Long> visitorIds){
        List<Visitor> visitors = new ArrayList<>();
        if (visitorIds==null){
            return null;
        }
        for(Long visitorId: visitorIds){
            visitors.add(visitorRepository.findById(visitorId).orElseThrow(()->
                    new EntityNotFoundException("Visitor with id %d not found".formatted(visitorId))));
        }
        return visitors;
    }
}
