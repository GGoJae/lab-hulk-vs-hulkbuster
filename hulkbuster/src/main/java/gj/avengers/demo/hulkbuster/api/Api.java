package gj.avengers.demo.hulkbuster.api;

import gj.avengers.demo.hulkbuster.api.dto.BeAttackedRequest;
import gj.avengers.demo.hulkbuster.service.DamageDetectionSystem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hulkbuster")
@RequiredArgsConstructor
public class Api {

    private final DamageDetectionSystem damageDetectionSystem;

    @PostMapping("/attack")
    public ResponseEntity<Void> beAttacked(@RequestBody BeAttackedRequest req) {
        damageDetectionSystem.getDamage(req.part(), req.damage());

        return ResponseEntity.noContent().build();
    }
}
