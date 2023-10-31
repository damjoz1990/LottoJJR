package pl.lotto.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record InputNumberResultDto(String message, LocalDateTime drawData, String ticketId, Set<Integer> numbersFromUser) {
}
