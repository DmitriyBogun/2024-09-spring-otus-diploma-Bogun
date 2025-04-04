package hw.diploma.fc.service;

import hw.diploma.fc.dto.Visitor;
import hw.diploma.fc.dto.VisitorClipCard;
import hw.diploma.fc.dto.VisitorClipCardCreateDto;
import hw.diploma.fc.dto.VisitorCreateDto;

import java.util.List;

public interface VisitorCardService {

    List<VisitorClipCard> getCards();
    VisitorClipCard getCard(Long id);
    VisitorClipCard createCard(VisitorClipCardCreateDto createCard);
    void deleteCard(Long id);
}
