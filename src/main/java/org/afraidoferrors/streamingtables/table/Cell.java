package org.afraidoferrors.streamingtables.table;

/**
 * Represents a Cell in a Table
 * 
 * @author Martin Weik
 *
 * @param <T> Type of data that are stored in {@link Cell}
 */
public interface Cell<T> {

	public T value();
}
