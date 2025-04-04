package hw.diploma.fc.service;

import hw.diploma.fc.dto.Visitor;
import hw.diploma.fc.dto.VisitorCreateDto;

import java.util.List;

public interface VisitorService {

    List<Visitor> getVisitors();
    Visitor getVisitorById(Long id);
    Visitor createVisitor(VisitorCreateDto visitorCreateDto);
    void deleteVisitor(Long id);
}
