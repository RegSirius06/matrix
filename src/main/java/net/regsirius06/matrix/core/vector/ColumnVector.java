package net.regsirius06.matrix.core.vector;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

public class ColumnVector extends AbstractVector {
    public ColumnVector(double @NotNull ... elements) {
        this(Arrays.stream(elements).boxed().toList());
    }

    public ColumnVector(@NotNull Collection<Double> elements) {
        super(elements.size(), 1, elements);
    }

    @Override
    public RowVector transposition() {
        return new RowVector(this.elements);
    }
}
