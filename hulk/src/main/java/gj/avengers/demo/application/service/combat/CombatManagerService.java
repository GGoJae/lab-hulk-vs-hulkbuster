package gj.avengers.demo.application.service.combat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class CombatManagerService {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final CombatService combatService;
    private final AtomicInteger turn = new AtomicInteger(0);
    private ScheduledFuture<?> attackJob;

    public void combatStart() {
        if (attackJob != null && !attackJob.isCancelled()) {
            return;
        }

        attackJob = taskScheduler.scheduleAtFixedRate(
                () -> combatService.attackOnHulkbuster(turn.incrementAndGet())
                , Duration.ofSeconds(2));

        log.info("헐크!!! 공격 시작!!!");
    }

    public void combatOver() {
        if (attackJob != null) {
            attackJob.cancel(false);
            attackJob = null;
            log.info("헐크 공격 종료");
        }
    }


}
