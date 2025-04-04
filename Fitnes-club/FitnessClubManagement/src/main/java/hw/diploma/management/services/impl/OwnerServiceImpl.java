package hw.diploma.management.services.impl;

import hw.diploma.management.dto.OwnerCreateDto;
import hw.diploma.management.dto.OwnerDto;
import hw.diploma.management.mappers.OwnerMapper;
import hw.diploma.management.models.Owner;
import hw.diploma.management.repositories.OwnerRepository;
import hw.diploma.management.services.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper;

    @Transactional(readOnly = true)
    @Override
    public List<OwnerDto> findAll() {

        return ownerRepository.findAll()
                .stream()
                .map(ownerMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OwnerDto create(OwnerCreateDto ownerCreateDto) {
        Owner ownerForSave = new Owner(null, ownerCreateDto.getFullName());
        OwnerDto ownerDtoAfterSave = ownerMapper.modelToDto(ownerRepository.save(ownerForSave));
        return ownerDtoAfterSave;
    }
}
