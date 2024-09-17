package net.regsirius06.matrix.core.vector;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

public class RowVector extends AbstractVector {
    public RowVector(double @NotNull ... elements) {
        this(Arrays.stream(elements).boxed().toList());
    }

    public RowVector(@NotNull Collection<Double> elements) {
        super(1, elements.size(), elements);
    }

    @Override
    public ColumnVector transposition() {
        return new ColumnVector(this.elements);
    }
}
