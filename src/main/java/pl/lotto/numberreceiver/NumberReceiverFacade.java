package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.InputNumberResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/*
* Klient podaje 6liczb
* liczby muszą być w zakresie od 1-99
* liczby nie mogą się powtarzać
* klient dostaje informacje o dacie losowania
* klient dostaje informacje o swoim unikalnym identyfikatorze losowania
* 
* */


@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator validator;
    private NumberReceiverRepository repository;
    private final Clock clock;

    public InputNumberResultDto inputNumbers(Set<Integer> numberFromUser){
        boolean areAllNumbersRange = validator.areAllNumbersRange(numberFromUser);
        if (areAllNumbersRange) {
            String ticketId = UUID.randomUUID().toString();
            LocalDateTime drawData = LocalDateTime.now(clock);
            Ticket savedTicket = repository.save(new Ticket(ticketId, drawData, numberFromUser));
            return InputNumberResultDto.builder()
                    .drawData(savedTicket.drawData())
                    .ticketId(savedTicket.ticketId())
                    .numbersFromUser(numberFromUser)
                    .message("success")
                    .build();
        }
        return InputNumberResultDto.builder()
                .message("failed")
                .build();
    }

    public List<TicketDto> userNumbers(LocalDateTime date) {
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate
                .stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }


}
