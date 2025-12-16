package gj.avengers.demo.application.service.combat;

import gj.avengers.demo.domain.event.HulkAngryEvent;
import gj.avengers.demo.domain.event.HulkInabilityToFightEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CombatScheduleControllerService {

    private final CombatManagerService combatManagerService;

    @EventListener
    public void hulkAngry(HulkAngryEvent event) {
        combatManagerService.combatStart();
    }

    @EventListener
    public void hulkInabilityToFight(HulkInabilityToFightEvent event) {
        combatManagerService.combatOver();
    }
}
