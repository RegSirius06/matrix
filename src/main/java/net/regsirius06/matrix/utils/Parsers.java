package net.regsirius06.matrix.utils;

import net.regsirius06.matrix.core.Matrix;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ExecutionException;

public final class Parsers {
    private Parsers() {
    }

    @Contract("_ -> new")
    public static @NotNull Pair<Integer, Integer> dimensionParser(@NotNull String s) {
        String[] sepS = s.split("x");
        int first = Integer.parseInt(sepS[0]), second = Integer.parseInt(sepS[1]);
        return new Pair<>(first, second);
    }

    public static @NotNull Matrix matrixParser(@NotNull String s) {
        int n = (int) s.lines().count(), m = 0;
        List<Double> list = new ArrayList<>();
        for (String i : s.lines().toList()) {
            List<String> x = Arrays.stream(i.split(" ")).toList();
            m = x.size();
            for(String j : x) {
                try {
                    list.add(Double.valueOf(j));
                } catch (Exception e) {
                    list.add(fractionsParser(j));
                }
            }
        }
        return Matrix.create(n, m, list);
    }

    public static double fractionsParser(@NotNull String s) {
        String[] arr = s.split("/");
        if (arr.length != 2) {
            throw new NoSuchElementException("The fraction is given incorrectly!");
        }
        return Double.parseDouble(arr[0]) / Double.parseDouble(arr[1]);
    }

    public static @NotNull String @NotNull [] commandParser(@NotNull String s) {
        return s.split(" ");
    }
}
