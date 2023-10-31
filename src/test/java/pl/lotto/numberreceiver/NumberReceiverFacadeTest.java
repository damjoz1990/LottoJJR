package pl.lotto.numberreceiver;

import org.junit.jupiter.api.Test;
import pl.lotto.AdjustableClock;
import pl.lotto.numberreceiver.dto.InputNumberResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.awt.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2023,2,15,11,0,0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumberValidator(),
            new InMemoryNumberReceiverRepositoryTestImpl(),
            clock
    );



    @Test
    public void schould_return_success_when_user_gave_six_numbers(){
        // given
        Set<Integer> numberFromUser = Set.of(1, 2, 3, 4, 5, 6);
        // when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numberFromUser);
        // then
        assertThat(result.message()).isEqualTo("success");
    }
    @Test
    public void schould_return_success_when_user_gave_less_than_six_numbers(){
        // given
        Set<Integer> numberFromUser = Set.of(1, 2, 3, 4, 5);
        // when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numberFromUser);
        // then
        assertThat(result.message()).isEqualTo("failed");
    }
    @Test
    public void schould_return_success_when_user_gave_at_lest_one_number_out_of_range_of_1_to_99(){
        // given
        Set<Integer> numberFromUser = Set.of(1, 2000, 3, 4, 5, 6);
        // when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numberFromUser);
        // then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void schould_return_save_to_database_when_user_gave_six_numbers(){
        // given
        Set<Integer> numberFromUser = Set.of(1, 2, 3, 4, 5, 6);
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numberFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2023,2,15,12,0,0);
        // when
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(drawDate);
        // then
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId(result.ticketId())
                        .drawDate(drawDate)
                        .numberFromUser(result.numbersFromUser())
                        .build()
        );
    }

}