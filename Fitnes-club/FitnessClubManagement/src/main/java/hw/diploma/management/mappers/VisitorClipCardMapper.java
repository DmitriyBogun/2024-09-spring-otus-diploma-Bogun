package hw.diploma.management.mappers;

import hw.diploma.management.dto.VisitorClipCardDto;
import hw.diploma.management.models.VisitorClipCard;
import org.springframework.stereotype.Component;

@Component
public class VisitorClipCardMapper {

    public VisitorClipCardDto modelToDto(VisitorClipCard visitorClipCard){
    VisitorClipCardDto visitorClipCardDto = new VisitorClipCardDto(visitorClipCard.getId(),
            visitorClipCard.getVisitor().getFullName(), visitorClipCard.getTrainerName(),
            visitorClipCard.getWorkoutName(), visitorClipCard.getNumberOfWorkouts());

    return visitorClipCardDto;
    }
}
