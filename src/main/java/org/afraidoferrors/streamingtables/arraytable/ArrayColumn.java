package org.afraidoferrors.streamingtables.arraytable;

import org.afraidoferrors.streamingtables.table.Cell;
import org.afraidoferrors.streamingtables.table.Column;

public class ArrayColumn<T> implements Column<T> {

	private int col;
	private T[][] data;
	
	public ArrayColumn(int position, T[][] data) {
		this.col = position;
		this.data = data;
	}

	@Override
	public int position() {
		return col;
	}

	@Override
	public Cell<T> cellAt(int position) {
		return new ArrayCell<>(position, this.col, this.data);
	}

}
