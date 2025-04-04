package hw.diploma.management.services.impl;

import hw.diploma.management.dto.VisitorCreateDto;
import hw.diploma.management.dto.VisitorDto;
import hw.diploma.management.dto.VisitorUpdateDto;
import hw.diploma.management.exceptions.EntityNotFoundException;
import hw.diploma.management.mappers.VisitorMapper;
import hw.diploma.management.models.Visitor;
import hw.diploma.management.repositories.VisitorRepository;
import hw.diploma.management.services.VisitorService;
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
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    private final VisitorMapper visitorMapper;

    @Transactional(readOnly = true)
    @Override
    public VisitorDto getVisitorById(Long id) {
        return visitorMapper.modelToDto(visitorRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Visitor with id = %d is not found"
                        .formatted(id))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<VisitorDto> getAllVisitors() {
        return visitorRepository.findAll()
                .stream()
                .map(visitorMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public VisitorDto create(VisitorCreateDto visitorCreateDto) {
        Visitor visitorForSave = new Visitor(null,visitorCreateDto.getFullName(), visitorCreateDto.getAge(),
                new ArrayList<>());

        VisitorDto visitorDtoAfterSave = visitorMapper.modelToDto(visitorRepository.save(visitorForSave));
        return visitorDtoAfterSave;
    }

    @Transactional
    @Override
    public VisitorDto update(VisitorUpdateDto visitorUpdateDto) {
        Visitor visitor = visitorRepository.findById(visitorUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Visitor with fullName %s not found".formatted((visitorUpdateDto.getFullName()))));

        visitor.setFullName(visitorUpdateDto.getFullName());
        visitor.setAge(visitorUpdateDto.getAge());

        Visitor visitorAfterSave = visitorRepository.save(visitor);
        return visitorMapper.modelToDto(visitorAfterSave);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        visitorRepository.deleteById(id);
    }
}
