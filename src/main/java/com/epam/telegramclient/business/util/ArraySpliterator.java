package com.epam.telegramclient.business.util;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

@RequiredArgsConstructor
public class ArraySpliterator<T> {

    private final int piecesSize;

    @SafeVarargs
    public final List<List<T>> split(T... items) {
        List<T> list = Arrays.stream(items).toList();

        List<List<T>> result = new ArrayList<>();

        for (int i = 0; i < items.length; i += piecesSize) {
            List<T> piece = list.subList(i, min(list.size(), i + piecesSize));
            result.add(piece);
        }

        return result;
    }
}