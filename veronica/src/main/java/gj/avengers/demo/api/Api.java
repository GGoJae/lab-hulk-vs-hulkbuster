package gj.avengers.demo.api;

import gj.avengers.demo.api.request.RequestPartRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Api {

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("베로니카 상태 양호");
    }

    @PostMapping("/request-parts")
    public ResponseEntity<Void> requestParts(
            @Valid @RequestBody RequestPartRequest request
            ) {

        log.info("파츠 배송 요청 : {}", request);

        return ResponseEntity.ok(null);
    }
}
