package net.regsirius06.matrix.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class SquareMatrix extends DefaultMatrix {
    protected SquareMatrix(int number_of_rows, @NotNull Collection<Double> elements) {
        super(number_of_rows, number_of_rows, elements);
    }

    @Override
    public @NotNull Double determinant() {
        if (this.n == 1) {
            return this.get(1, 1);
        }
        if (this.n == 2) {
            return this.get(1, 1) * this.get(2, 2) -
                    this.get(1, 2) * this.get(2, 1);
        }
        double det = 0;
        for (int i = 0; i < this.n; i++) {
            det += this.get(1, i + 1) * algebraicComplements(1, i + 1);
        }
        return det;
    }

    @Override
    public @NotNull Double minor(int row, int column) {
        return this.getWithoutColumn(column).getWithoutRow(row).determinant();
    }

    @Override
    public @NotNull Double algebraicComplements(int row, int column) {
        return ((row + column) % 2 == 0 ? 1 : -1) * minor(row, column);
    }

    @Override
    public @Nullable Matrix inverse() {
        if (this.n == 1) return Matrix.create(1, 1, 1/this.get(1, 1));
        double det = this.determinant();
        if (det == 0) {
            return null;
        }
        Matrix buffer = Matrix.zeros(this.n, this.m);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                buffer.set(i + 1, j + 1,
                        this.algebraicComplements(i + 1, j + 1));
            }
        }
        return buffer.transposition().multiplyBy(1 / det);
    }
}
