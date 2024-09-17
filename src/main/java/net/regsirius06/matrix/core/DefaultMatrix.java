package net.regsirius06.matrix.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultMatrix extends Matrix {
    protected DefaultMatrix(int number_of_rows, int number_of_columns, @NotNull Collection<Double> elements) {
        super(number_of_rows, number_of_columns, elements);
    }

    @Override
    public Matrix transposition() {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(this.elements.get(i + j * m));
            }
        }
        return Matrix.create(this.m, this.n, list);
    }

    @Override
    public @Nullable Double determinant() {
        return null;
    }

    @Override
    public @Nullable Double minor(int row, int column) {
        return null;
    }

    @Override
    public @Nullable Double algebraicComplements(int row, int column) {
        return null;
    }

    @Override
    public @Nullable Matrix inverse() {
        return null;
    }
}
