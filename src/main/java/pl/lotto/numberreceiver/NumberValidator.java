package pl.lotto.numberreceiver;

import java.util.Set;

class NumberValidator {


    private static final int MAX_NUMBER_FROM_USER = 6;
    private static final int MINIMAL_NUMBERS_FROM_USER = 1;
    private static final int MAXIMAL_NUMBERS_FROM_USER = 99;

    boolean areAllNumbersRange(Set<Integer> numberFromUSer) {
        return numberFromUSer.stream()
                .filter(number -> number >= MINIMAL_NUMBERS_FROM_USER)
                .filter(number -> number <= MAXIMAL_NUMBERS_FROM_USER)
                .count() == MAX_NUMBER_FROM_USER;

    }
}
