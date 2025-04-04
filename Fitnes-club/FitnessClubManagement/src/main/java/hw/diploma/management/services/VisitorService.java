package hw.diploma.management.services;

import hw.diploma.management.dto.VisitorCreateDto;
import hw.diploma.management.dto.VisitorDto;
import hw.diploma.management.dto.VisitorUpdateDto;

import java.util.List;

public interface VisitorService {

    VisitorDto getVisitorById(Long id);

    List<VisitorDto> getAllVisitors();

    VisitorDto create(VisitorCreateDto bookDto);

    VisitorDto update(VisitorUpdateDto bookDto);

    void deleteById(Long id);
}
