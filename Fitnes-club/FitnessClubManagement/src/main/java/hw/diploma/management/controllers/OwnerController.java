package hw.diploma.management.controllers;


import hw.diploma.management.dto.OwnerCreateDto;
import hw.diploma.management.dto.OwnerDto;
import hw.diploma.management.services.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerDto> getOwnerList() {
        log.info("getOwnerList()");
        return ownerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDto createOwner(@Valid @RequestBody OwnerCreateDto ownerCreateDto){
        log.info("createOwner(): dto {}", ownerCreateDto);
        return ownerService.create(ownerCreateDto);
    }

}
