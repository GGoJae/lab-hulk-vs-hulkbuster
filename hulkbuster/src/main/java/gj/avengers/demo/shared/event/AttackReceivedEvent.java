package gj.avengers.demo.shared.event;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;

public record AttackReceivedEvent(
        HulkBuster.TotalState state) {
}
