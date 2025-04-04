package hw.diploma.management.services.impl;

import hw.diploma.management.dto.ReportDto;
import hw.diploma.management.dto.TrainerDto;
import hw.diploma.management.dto.VisitorDto;
import hw.diploma.management.mappers.TrainerMapper;
import hw.diploma.management.mappers.VisitorMapper;
import hw.diploma.management.repositories.TrainerRepository;
import hw.diploma.management.repositories.VisitorRepository;
import hw.diploma.management.services.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final TrainerRepository trainerRepository;

    private final VisitorRepository visitorRepository;

    private final VisitorMapper visitorMapper;

    private final TrainerMapper trainerMapper;

    @Transactional(readOnly = true)
    @Override
    public ReportDto create() {

        List<TrainerDto> trainers = trainerRepository.findAll()
                .stream().
                map(trainerMapper::modelToDto).
                collect(Collectors.toList());
        List<VisitorDto> visitors = visitorRepository.findAll().stream().
                map(visitorMapper::modelToDto).
                collect(Collectors.toList());

        ReportDto report = new ReportDto(trainers, visitors);
        return report;
    }
}
