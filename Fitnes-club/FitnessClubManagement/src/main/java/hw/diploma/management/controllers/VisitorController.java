package hw.diploma.management.controllers;

import hw.diploma.management.dto.VisitorCreateDto;
import hw.diploma.management.dto.VisitorDto;
import hw.diploma.management.dto.VisitorUpdateDto;
import hw.diploma.management.services.VisitorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/visitor")
public class VisitorController {

    private final VisitorService visitorService;

    @GetMapping
    public List<VisitorDto> getAllVisitors(){
        log.info("getAllVisitors()");
        return visitorService.getAllVisitors();
    }

    @GetMapping("/{id}")
    public VisitorDto getVisitor(@PathVariable(value = "id", required = false) Long id){
        log.info("getVisitor(): id {}", id);
        return visitorService.getVisitorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitorDto createVisitor(@Valid @RequestBody VisitorCreateDto visitorCreateDto){
        log.info("createVisitor(): dto {}", visitorCreateDto);
        return visitorService.create(visitorCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public VisitorDto updateVisitor(@PathVariable(value = "id") Long id,
                                    @Valid @RequestBody VisitorUpdateDto visitorUpdateDto){
        log.info("updateVisitor(): dto {}", visitorUpdateDto);
        visitorUpdateDto.setId(id);
        return visitorService.update(visitorUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVisitorByName(@PathVariable(value = "id") Long id){
        log.info("deleteVisitorByName(): id {}", id);
        visitorService.deleteById(id);
    }
}
