package org.afraidoferrors.streamingtables.modeltable;

import org.afraidoferrors.streamingtables.table.Cell;

/**
 * A ModelQueue stores the values selected by {@link ReferenceCell}.
 * 
 * @author Martin Weik
 *
 * @param <T>
 *            Type of data that are stored in {@link Cell} and
 *            {@link ModelQueue}.
 */
public interface ModelQueue<T> {

	public int row();

	public int column();

	public T poll();

}
