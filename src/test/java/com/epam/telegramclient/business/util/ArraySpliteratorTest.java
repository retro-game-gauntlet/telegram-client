package com.epam.telegramclient.business.util;

import com.epam.telegramclient.tags.Junit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Junit
class ArraySpliteratorTest {

    private ArraySpliterator<Integer> arraySpliterator;

    @Test
    void shouldArrangeOneItemPerRow() {
        arraySpliterator = new ArraySpliterator<>(1);

        List<List<Integer>> ints = arraySpliterator.split(1, 2, 3);

        assertThat(ints).hasToString("[" +
                "[1], " +
                "[2], " +
                "[3]]");
    }

    @Test
    void shouldArrangeTwoItemsPerRow() {
        arraySpliterator = new ArraySpliterator<>(2);

        List<List<Integer>> ints = arraySpliterator.split(1, 2, 3, 4, 5);

        assertThat(ints).hasToString("[" +
                "[1, 2], " +
                "[3, 4], " +
                "[5]]");
    }
}