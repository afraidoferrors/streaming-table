package org.afraidoferrors.streamingtables.arraytable;

import org.afraidoferrors.streamingtables.modeltable.ModelTable;
import org.afraidoferrors.streamingtables.table.Table;

/**
 * Implementation of a streaming table based on a twodimensional array.
 * 
 * @author Martin Weik
 *
 * @param <T> type of the values stored inside table
 */
public class ArrayTable<T> implements Table<T> {

	private final T[][] data;

	public ArrayTable(T[][] data) {
		this.data = data;
	}

	@Override
	public ModelTable<T> modelstream() {
		return new ArrayModelTable<>(data);
	}

}
