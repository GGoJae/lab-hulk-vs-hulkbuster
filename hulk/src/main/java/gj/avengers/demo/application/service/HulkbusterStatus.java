package gj.avengers.demo.application.service;

import gj.avengers.demo.application.model.PartCondition;
import gj.avengers.demo.application.model.PartWithCondition;
import gj.avengers.demo.domain.model.TargetPart;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HulkbusterStatus {

    private final Map<TargetPart, TurnAndCond> statusMap = new EnumMap<>(TargetPart.class);
    private final ReentrantLock reentrantLock = new ReentrantLock();

    @PostConstruct
    public void init() {
        statusMap.put(TargetPart.HELMETS, new TurnAndCond(-1, PartCondition.UNNOWN));
        statusMap.put(TargetPart.ARMOR, new TurnAndCond(-1, PartCondition.UNNOWN));
        statusMap.put(TargetPart.ARMS, new TurnAndCond(-1, PartCondition.UNNOWN));
        statusMap.put(TargetPart.LEGS, new TurnAndCond(-1, PartCondition.UNNOWN));
    }

    public void updateCurrentState(int turn, PartWithCondition partWithCondition) {
        reentrantLock.lock();
        try {
            TargetPart part = partWithCondition.part();
            PartCondition cond = partWithCondition.condition();
            statusMap.compute(
                    part,
                    (p, tac) -> {
                        if (tac == null) return new TurnAndCond(turn, cond);

                        return tac.turn < turn ? new TurnAndCond(turn, cond) : tac;
                    }
            );
        } finally {
            reentrantLock.unlock();
        }

    }

    public Map<TargetPart, PartCondition> currentState() {
        reentrantLock.lock();
        try {
            return statusMap.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().condition,
                            (a, b) -> {
                                log.info("컨디션 충돌..{}, {}", a, b);
                                return a;
                            },
                            HashMap::new
                    ));
        } finally {
            reentrantLock.unlock();
        }
    }

    private record TurnAndCond(
            int turn,
            PartCondition condition
    ) {

    }
}
