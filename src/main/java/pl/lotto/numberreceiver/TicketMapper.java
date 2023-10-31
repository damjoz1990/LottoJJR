package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketDto;

public class TicketMapper {

    public static TicketDto mapFromTicket(Ticket ticket){
        return TicketDto.builder()
                .numberFromUser(ticket.numberFromUser())
                .ticketId(ticket.ticketId())
                .drawDate(ticket.drawData())
                .build();
    }
}
