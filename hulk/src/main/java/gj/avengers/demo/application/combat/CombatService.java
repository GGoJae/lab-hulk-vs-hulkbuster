package gj.avengers.demo.application.combat;

import gj.avengers.demo.application.out.HulkbusterPort;
import gj.avengers.demo.domain.hulk.Hulk;
import gj.avengers.demo.domain.model.TargetPart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor
public class CombatService {

    private final HulkbusterPort hulkbusterPort;
    private final Hulk hulk;
    private final AtomicBoolean inFlight = new AtomicBoolean(false);

    public void attackOnHulkbuster() {
        if (!inFlight.compareAndSet(false, true)) return;

        TargetPart targetPart = choiceTargetPart();

        hulkbusterPort.attackOnHulkbuster(targetPart, hulk.totalPower())
                .whenComplete((v, ex) -> {
                    inFlight.set(false);
                    if (ex != null) {
                        log.error("헐크버스터 공격 실패", ex);
                    }
                });
    }

    private TargetPart choiceTargetPart() {
        int nextInt = ThreadLocalRandom.current().nextInt(TargetPart.values().length);
        return TargetPart.values()[nextInt];
    }


}
