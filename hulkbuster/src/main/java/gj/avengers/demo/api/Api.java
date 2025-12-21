package gj.avengers.demo.api;

import gj.avengers.demo.api.request.BeAttackedRequest;
import gj.avengers.demo.api.response.BeAttackedResponse;
import gj.avengers.demo.application.damageDetection.DamageDetectionSystem;
import gj.avengers.demo.domain.hulkbuster.model.DamageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Api {

    private final DamageDetectionSystem damageDetectionSystem;

    @PostMapping("/attack")
    public ResponseEntity<BeAttackedResponse> beAttacked(@RequestBody BeAttackedRequest req) {
        DamageResult damageResult = damageDetectionSystem.getDamage(req.part(), req.damage());

        return ResponseEntity.ok(BeAttackedResponse.from(damageResult));
    }
}
