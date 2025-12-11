package gj.avengers.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hulk")
public class Api {

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("헐크!! 헐크!!");
    }
}
