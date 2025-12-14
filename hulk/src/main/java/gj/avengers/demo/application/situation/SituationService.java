package gj.avengers.demo.application.situation;

import gj.avengers.demo.api.request.OnAttackRequest;
import gj.avengers.demo.api.response.OnAttackResponse;
import gj.avengers.demo.shared.model.BodyPart;
import gj.avengers.demo.shared.model.Reaction;
import gj.avengers.demo.domain.hulk.Hulk;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SituationService {

    private final Hulk hulk;

    public OnAttackResponse onAttack(OnAttackRequest req) {
        Reaction reaction = hulk.getDamage(req.targetPart());

        return new OnAttackResponse(reaction);
    }
}
