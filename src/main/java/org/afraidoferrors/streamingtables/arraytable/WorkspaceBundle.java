package org.afraidoferrors.streamingtables.arraytable;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.afraidoferrors.streamingtables.modeltable.ReferenceCell;
import org.afraidoferrors.streamingtables.table.Cell;
import org.afraidoferrors.streamingtables.table.Column;
import org.afraidoferrors.streamingtables.table.Row;

class WorkspaceBundle<T> {

	Predicate<Column<T>> onColumns = c -> true;
	Predicate<Row<T>> onRows = r -> true;
	Predicate<Cell<T>> onCells = c -> true;
	Consumer<ReferenceCell<T>> cellCursor;

	public WorkspaceBundle() {
		super();
		
		
	}
	
	public WorkspaceBundle<T> copy() {
		WorkspaceBundle<T> bundle = new WorkspaceBundle<>();
		bundle.onRows = this.onRows;
		bundle.onColumns = this.onColumns;
		bundle.onCells = this.onCells;
		bundle.cellCursor = this.cellCursor;
		return bundle;
	}

}
