package org.afraidoferrors.streamingtables.arraytable;

import org.afraidoferrors.streamingtables.table.Cell;
import org.afraidoferrors.streamingtables.table.Row;

public class ArrayRow<T> implements Row<T> {
	private int row;
	private T[][] data;
	
	public ArrayRow(int position, T[][] data) {
		this.row = position;
		this.data = data;
	}

	@Override
	public int position() {
		return row;
	}

	@Override
	public Cell<T> cellAt(int position) {
		return new ArrayCell<>(this.row, position, this.data);
	}

}
