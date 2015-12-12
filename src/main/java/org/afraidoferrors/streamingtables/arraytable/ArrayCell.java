package org.afraidoferrors.streamingtables.arraytable;

import org.afraidoferrors.streamingtables.table.Cell;

/**
 * 
 * @author Martin Weik
 *
 * @param <T>
 */
public class ArrayCell<T> implements Cell<T> {

	private final int row;
	private final int column;
	private final T[][] data;

	public ArrayCell(int row, int column, T[][] data) {
		this.row = row;
		this.column = column;
		this.data = data;
	}

	@Override
	public T value() {
		return this.data[row][column];
	}

}
