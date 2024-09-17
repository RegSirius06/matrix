package net.regsirius06.matrix.core.vector;

import net.regsirius06.matrix.core.DefaultMatrix;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

abstract class AbstractVector extends DefaultMatrix {

    protected AbstractVector(int number_of_rows, int number_of_columns, @NotNull Collection<Double> elements) {
        super(number_of_rows, number_of_columns, elements);
    }

    public int length() {
        return Math.max(this.getRows(), this.getColumns());
    }

    @Override
    public abstract AbstractVector transposition();
}
