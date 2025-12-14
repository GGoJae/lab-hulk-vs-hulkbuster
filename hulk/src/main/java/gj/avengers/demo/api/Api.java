package gj.avengers.demo.api;

import gj.avengers.demo.api.response.OnAttackResponse;
import gj.avengers.demo.api.request.OnAttackRequest;
import gj.avengers.demo.application.service.HulkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Api {

    private final HulkService hulkService;

    @PostMapping("/banner/slap")
    public ResponseEntity<Void> bannerAttack() {
        hulkService.bannerSlap();

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/attack")
    public ResponseEntity<OnAttackResponse> onAttack(
            @Valid @RequestBody OnAttackRequest request) {

        return ResponseEntity.ok(
                OnAttackResponse.from(hulkService.onAttack(request)));
    }
}
