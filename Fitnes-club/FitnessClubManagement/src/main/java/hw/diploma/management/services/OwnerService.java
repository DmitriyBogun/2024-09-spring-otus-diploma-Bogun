package hw.diploma.management.services;

import hw.diploma.management.dto.OwnerCreateDto;
import hw.diploma.management.dto.OwnerDto;

import java.util.List;

public interface OwnerService {

    List<OwnerDto> findAll();

    OwnerDto create(OwnerCreateDto ownerCreateDto);
}
