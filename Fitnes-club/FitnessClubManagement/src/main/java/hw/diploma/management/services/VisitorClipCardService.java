package hw.diploma.management.services;

import hw.diploma.management.dto.VisitorClipCardCreateDto;
import hw.diploma.management.dto.VisitorClipCardDto;
import hw.diploma.management.dto.VisitorClipCardUpdateDto;

import java.util.List;

public interface VisitorClipCardService {

    VisitorClipCardDto getClipCardById(Long id);

    List<VisitorClipCardDto> findAllCard();

    VisitorClipCardDto create(VisitorClipCardCreateDto visitorClipCardCreateDto);

    VisitorClipCardDto update(VisitorClipCardUpdateDto visitorClipCardUpdateDto);

    VisitorClipCardDto subtractWorkout(Long id);

    void deleteClipCardById(Long id);
}
