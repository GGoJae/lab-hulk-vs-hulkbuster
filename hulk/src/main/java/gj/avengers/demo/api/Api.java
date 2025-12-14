package gj.avengers.demo.api;

import gj.avengers.demo.api.response.OnAttackResponse;
import gj.avengers.demo.api.request.OnAttackRequest;
import gj.avengers.demo.application.situation.SituationService;
import gj.avengers.demo.domain.hulk.Hulk;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Api {

    private final SituationService situationService;

    @PostMapping("/attack")
    public ResponseEntity<OnAttackResponse> onAttack(
            @Valid @RequestBody OnAttackRequest request) {

        return ResponseEntity.ok(situationService.onAttack(request));
    }
}
