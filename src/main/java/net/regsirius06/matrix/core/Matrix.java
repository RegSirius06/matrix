package net.regsirius06.matrix.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

public abstract class Matrix {
    protected final int n;
    protected final int m;

    protected final List<Double> elements;

    protected Matrix(int number_of_rows, int number_of_columns, double @NotNull ... elements) {
        this(number_of_rows, number_of_columns, Arrays.stream(elements).boxed().toList());
    }

    protected Matrix(int number_of_rows, int number_of_columns, @NotNull Collection<Double> elements) {
        this.n = number_of_rows;
        this.m = number_of_columns;
        if (this.n < 1 || this.m < 1) {
            throw new NoSuchElementException("The number of rows and columns is an integer greater than zero.");
        }
        if (elements.size() != n * m) {
            throw new NoSuchElementException("There must be exactly " + n + "*" + m + " (" + n * m + ") elements.");
        }
        this.elements = new ArrayList<>(elements);
    }

    public Matrix copy() {
        return Matrix.create(this.n, this.m, this.elements);
    }

    @Contract("_, _, _ -> new")
    @SafeVarargs
    public static @NotNull Matrix create(int number_of_rows, int number_of_columns, @NotNull Double ... elements) {
        return Matrix.create(number_of_rows, number_of_columns, Arrays.asList(elements));
    }

    @Contract("_, _, _ -> new")
    public static @NotNull Matrix create(int number_of_rows, int number_of_columns, @NotNull Collection<Double> elements) {
        if (number_of_rows == number_of_columns) {
            return new SquareMatrix(number_of_rows, elements);
        }
        return new DefaultMatrix(number_of_rows, number_of_columns, elements);
    }

    public static @NotNull Matrix zeros(int number_of_rows, int number_of_columns) {
        return Matrix.create(number_of_rows, number_of_columns,
                "0\n".repeat(number_of_columns * number_of_rows)
                        .lines().map(e -> 0D).toList());
    }

    public static @NotNull Matrix ones(int number_of_rows, int number_of_columns) {
        return Matrix.create(number_of_rows, number_of_columns,
                "1\n".repeat(number_of_columns * number_of_rows)
                        .lines().map(e -> 1D).toList());
    }

    public static @NotNull Matrix identityMatrix(int dimension) {
        return Matrix.create(dimension, dimension, IntStream.range(0, dimension * dimension)
                .mapToObj(index -> index / dimension == index % dimension ? 1D : 0D).toList());
    }

    public static @NotNull Matrix sameNumbers(int number_of_rows, int number_of_columns, double number) {
        return Matrix.create(number_of_rows, number_of_columns,
                "n\n".repeat(number_of_columns * number_of_rows)
                        .lines().map(e -> number).toList());
    }

    public String view() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                builder.append(this.elements.get(i * m + j)).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return this.view();
    }

    public double get(int i, int j) {
        return this.elements.get((i - 1) * m + j - 1);
    }

    public void set(int i, int j, double value) {
        this.elements.set((i - 1) * m + j - 1, value);
    }

    public int getRows() {
        return this.n;
    }

    public int getColumns() {
        return this.m;
    }

    public Matrix getWithoutColumn(int column) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if (j == column - 1) continue;
                list.add(this.elements.get(i * m + j));
            }
        }
        return Matrix.create(this.n, this.m - 1, list);
    }

    public Matrix getWithoutRow(int row) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < this.n; i++) {
            if (i == row - 1) continue;
            for (int j = 0; j < this.m; j++) {
                list.add(this.elements.get(i * m + j));
            }
        }
        return Matrix.create(this.n - 1, this.m, list);
    }

    public Matrix addTo(@NotNull Matrix otherMatrix) {
        if (this.n != otherMatrix.n || this.m != otherMatrix.m) {
            throw new IllegalArgumentException("Incompatible matrix sizes.");
        }
        List<Double> list = new ArrayList<>();
        IntStream.range(0, this.n * this.m).forEach(e -> list.add(this.elements.get(e) + otherMatrix.elements.get(e)));
        return Matrix.create(this.n, this.m, list);
    }

    public <N extends Number> Matrix multiplyBy(N k) {
        return Matrix.create(this.n, this.m,
                this.elements.stream().map(e -> e * k.doubleValue()).toList());
    }

    public Matrix multiplyBy(@NotNull Matrix otherMatrix) {
        if (this.m != otherMatrix.n) {
            throw new IllegalArgumentException("Incompatible matrix sizes.");
        }
        Matrix result = Matrix.zeros(this.n, otherMatrix.m);

        for (int i = 1; i <= this.n; i++) {
            for (int j = 1; j <= otherMatrix.m; j++) {
                double sum = 0;
                for (int k = 1; k <= this.m; k++) {
                    sum += this.get(i, k) * otherMatrix.get(k, j);
                }
                result.set(i, j, sum);
            }
        }

        return result;
    }

    public Matrix power(int exponent) {
        if (exponent < 1) {
            throw new NoSuchElementException("The exponent is an integer greater than zero.");
        }
        if (exponent == 1) return this;
        return this.multiplyBy(this.power(exponent - 1));
    }

    public abstract Matrix transposition();

    public abstract Double determinant();

    public abstract Double minor(int row, int column);

    public abstract Double algebraicComplements(int row, int column);

    public abstract Matrix inverse();
}
