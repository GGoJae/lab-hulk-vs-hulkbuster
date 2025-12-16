package gj.avengers.demo.application.service.combat;

import gj.avengers.demo.application.service.HulkbusterStatus;
import gj.avengers.demo.application.service.target_choice.TargetPartChoiceService;
import gj.avengers.demo.application.out.CombatResultRecorder;
import gj.avengers.demo.application.out.HulkbusterPort;
import gj.avengers.demo.domain.hulk.Hulk;
import gj.avengers.demo.domain.model.TargetPart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor
public class CombatService {

    private final HulkbusterPort hulkbusterPort;
    private final CombatResultRecorder recorder;
    private final Hulk hulk;
    private final HulkbusterStatus hulkbusterStatus;
    private final TargetPartChoiceService targetPartChoiceService;
    private final AtomicBoolean inFlight = new AtomicBoolean(false);

    public void attackOnHulkbuster(int turn) {
        if (!inFlight.compareAndSet(false, true)) return;

        TargetPart targetPart = targetPartChoiceService.choiceTargetPart();

        hulkbusterPort.attackOnHulkbuster(targetPart, hulk.totalPower())
                .handle((result, ex) -> {
                    if (ex != null) {
                        recorder.recordFailure(turn, targetPart, ex);
                        log.error("헐크버스터 공격 실패", ex);
                        return null;
                    }

                    try {
                        recorder.record(turn, result);
                        hulkbusterStatus.updateCurrentState(turn, result);
                    } catch (Exception postEx) {
                        log.error("post-proccessing failed", postEx);
                        // 요청 자체가 아닌 후처리 도중 예외 터진거
                    }
                    return null;
                })
                .whenComplete((v, ex2) -> {
                    inFlight.set(false);
                });
    }


}
