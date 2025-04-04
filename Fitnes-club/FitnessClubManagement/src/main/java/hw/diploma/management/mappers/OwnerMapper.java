package hw.diploma.management.mappers;

import hw.diploma.management.dto.OwnerCreateDto;
import hw.diploma.management.dto.OwnerDto;

import hw.diploma.management.models.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner dtoToModel(OwnerDto ownerDto);
    Owner dtoToModel(OwnerCreateDto ownerCreateDto);
    OwnerDto modelToDto(Owner owner);

}
