package gj.avengers.demo.application.service;

import gj.avengers.demo.api.request.OnAttackRequest;
import gj.avengers.demo.domain.event.DomainEvent;
import gj.avengers.demo.domain.event.HulkAngryEvent;
import gj.avengers.demo.domain.hulk.Hulk;
import gj.avengers.demo.shared.model.Reaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HulkService {

    private final Hulk hulk;
    private final ApplicationEventPublisher eventPublisher;

    public Reaction onAttack(OnAttackRequest req) {
        Reaction reaction = hulk.takeHit(req.targetPart());

        var domainEvents = hulk.pullEvents();
        if (!domainEvents.isEmpty()) {
            for (DomainEvent event : domainEvents) {
                eventPublisher.publishEvent(event);
            }
        }

        return reaction;
    }

    public void bannerSlap() {
        eventPublisher.publishEvent(new HulkAngryEvent());
    }
}
