package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;

//encvja do bazy
record Ticket(String ticketId, LocalDateTime drawData, Set<Integer> numberFromUser) {
}
