package gj.avengers.demo.shared.event;

import gj.avengers.demo.domain.hulkbuster.HulkBuster;

public record AttackReceivedEvent(
        HulkBuster.TotalState state) {
}
