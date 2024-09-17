package net.regsirius06.matrix.ui;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum TextFormatter {
    EMPTY("\033[0;0;0m"),
    RED("\033[0;1;91m"),
    BLUE("\033[0;1;96m"),
    GREEN("\033[0;1;92m"),
    YELLOW("\033[0;1;93m"),
    PURPLE("\033[0;1;95m"),
    WHITE("\033[0;1;97m"),
    WIN("\033[0;2;32m"),
    DRAW("\033[0;2;37m"),
    ERROR("\033[0;2;31m");

    private final String cod;

    TextFormatter(String cod) {
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    @Override
    public String toString() {
        return cod;
    }

    @Contract(pure = true)
    public @NotNull String format(String text) {
        return cod + text + TextFormatter.EMPTY;
    }

    public static void outConsole(String text, TextFormatter... formats) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(formats).forEach(builder::append);
        builder.append(text).append(TextFormatter.EMPTY);
        System.out.println(builder);
    }

    public static void outConsole(@NotNull Object object, TextFormatter... formats) {
        outConsole(object.toString(), formats);
    }
}
