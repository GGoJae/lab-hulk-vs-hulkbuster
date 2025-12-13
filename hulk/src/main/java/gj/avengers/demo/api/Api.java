package gj.avengers.demo.api;

import gj.avengers.demo.api.Response.OnAttackResponse;
import gj.avengers.demo.api.request.OnAttackRequest;
import gj.avengers.demo.domain.Hulk;
import gj.avengers.demo.domain.Reaction;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Api {

    private final Hulk hulk;

    @PostMapping("/attack")
    public ResponseEntity<OnAttackResponse> onAttack(
            @Valid @RequestBody OnAttackRequest request) {

        return ResponseEntity.ok(hulk.getDamage(request.targetPart()));
    }
}
