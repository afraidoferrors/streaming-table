package org.afraidoferrors.streamingtables.modeltable;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.afraidoferrors.streamingtables.table.Cell;
import org.afraidoferrors.streamingtables.table.Column;
import org.afraidoferrors.streamingtables.table.Row;

/**
 * Defines the data that are delivered to the Model Task in two steps:
 * <ol>
 * <li>Restrict the Workspace by defining Predicates on Rows, Columns and Cells.
 * Default behavior for a new Workspace is "no restrictions" so it works on
 * every cell.
 * Every selected cell with these Predicates is a reference point for the CellCursor.
 * </li>
 * <li>If there are headers or footers in the table that should be interpreted
 * define a predicate that selects a reference cell. (E.g. use every cell with
 * value "Warehouse" as reference and tell the CellCursor to consume the Cell on
 * the right.)
 * </li>
 * <li>Define the Cells that should be consumed 
 * </ol>
 * 
 * Invariants - A new Workspace has these defaults:
 * <ul>
 * <li>row... work on every row
 * </li>
 * <li>column... work on every column
 *</li>
 * <li>cell... work on every cell
 *</li>
 * <li>header... empty
 * </li>
 * <li>footer...empty
 * </li>
 * </ul>
 * 
 * @author Martin Weik
 *
 * @param <T>
 *            Type that is stored in the Cells.
 */
public interface Workspace<T> {
	/**
	 * Defines the {@link Row} on which the {@link ReferenceCell} should work.
	 * Working on {@link Cell} directly works also but working on rows leaves
	 * the implementation room for optimizations.
	 * 
	 * @param rowPredicate a predicate that decides for every given {@link Row} if it contains {@link Cell} that act as {@link ReferenceCell}.
	 * @return this Workspace
	 */
	public Workspace<T> onRows(Predicate<Row<T>> rowPredicate);

	/**
	 * Defines the {@link Column} on which the {@link ReferenceCell} should work.
	 * Working on {@link Cell} directly works also but working on columns leaves
	 * the implementation room for optimizations.
	 * 
	 * @param columnPredicat a predicate that decides for every given {@link Column} if it contains {@link Cell} that act as {@link ReferenceCell}.
	 * @return this Workspace
	 */
	public Workspace<T> onColumns(Predicate<Column<T>> columnPredicat);

	/**
	 * Defines the cells on which the {@link ReferenceCell} should work.
	 * 
	 * @param cellPredicate a predicate that decides for every given {@link Cell} if it acts as {@link ReferenceCell}.
	 * @return this Workspace
	 */
	public Workspace<T> onCells(Predicate<Cell<T>> cellPredicate);

	public Workspace<T> restore();

	public ModelTable<T> collect(Consumer<ReferenceCell<T>> cc);
	
	
	
	
	
}
