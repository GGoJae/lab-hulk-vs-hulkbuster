package gj.avengers.demo.application.service.target_choice.strategy;

import gj.avengers.demo.application.service.target_choice.TargetPartChoiceService;
import gj.avengers.demo.domain.model.TargetPart;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomTargetPartChoiceService implements TargetPartChoiceService {
    @Override
    public TargetPart choiceTargetPart() {
        int nextInt = ThreadLocalRandom.current().nextInt(TargetPart.values().length);
        return TargetPart.values()[nextInt];
    }
}
