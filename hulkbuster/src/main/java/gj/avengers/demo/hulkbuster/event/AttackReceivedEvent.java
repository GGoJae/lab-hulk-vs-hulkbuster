package gj.avengers.demo.hulkbuster.event;

import gj.avengers.demo.hulkbuster.domain.HulkBuster;

public record AttackReceivedEvent(
        HulkBuster.TotalState state) {
}
