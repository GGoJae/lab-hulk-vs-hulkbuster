package gj.avengers.demo.shared.event;

import gj.avengers.demo.domain.hulkbuster.model.Status;

public record AttackReceivedEvent(
        Status state) {
}
