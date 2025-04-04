package hw.diploma.management.mappers;

import hw.diploma.management.dto.VisitorClipCardDto;
import hw.diploma.management.dto.VisitorDto;
import hw.diploma.management.models.Visitor;
import hw.diploma.management.models.VisitorClipCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VisitorMapper {

    private final VisitorClipCardMapper visitorClipCardMapper;

    public VisitorDto modelToDto(Visitor visitor) {
        VisitorDto visitorDto = new VisitorDto(visitor.getId(),
                visitor.getFullName(), visitor.getAge(), getVisitorCardDtos(visitor.getCards()));
        return visitorDto;
    }

    private List<VisitorClipCardDto> getVisitorCardDtos(List<VisitorClipCard> visitorClipCard){

        List<VisitorClipCardDto> cardDtos = visitorClipCard
                .stream()
                .map(visitorClipCardMapper::modelToDto)
                .collect(Collectors.toList());
        return cardDtos;
    }
}
