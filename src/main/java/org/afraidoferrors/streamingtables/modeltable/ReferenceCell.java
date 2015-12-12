package org.afraidoferrors.streamingtables.modeltable;

import org.afraidoferrors.streamingtables.table.Cell;

/**
 * Represents a Cursor on a {@link Cell} to select values that should be
 * supplied to another function.
 * 
 * @author Martin Weik
 *
 *	@param <T>
 *            Type that is stored in every {@link Cell}
 *
 */
public interface ReferenceCell<T> {

	/**
	 * Enter a default value instead of selecting a {@link Cell}.
	 * 
	 * @param value
	 *            The value that should be passed.
	 */
	public void value(T value);

	/**
	 * Consumes the value of the actual cursor.
	 */
	public void it();

	/**
	 * Consumes the value of the cell {@code steps} steps above the actual
	 * cursor
	 * 
	 * @param steps
	 *            number of steps up.
	 */
	public void up(int steps);

	/**
	 * Consumes the value of the cell {@code steps} steps below the actual
	 * cursor.
	 * 
	 * @param steps
	 *            number of steps down.
	 */
	public void down(int steps);

	/**
	 * Consumes the value of the cell {@code steps} steps to the left the actual
	 * cursor.
	 * 
	 * @param steps
	 *            number of steps to the left.
	 */
	public void left(int steps);

	/**
	 * Consumes the value of the cell {@code steps} steps to the right the
	 * actual cursor.
	 * 
	 * @param steps
	 *            number of steps to the right.
	 */
	public void right(int steps);

	/**
	 * Consumes the value of the cell in row {@code row} and column of the
	 * actual cursor.
	 * 
	 * @param row
	 *            index of the row starting with 0.
	 */
	public void row(int row);

	/**
	 * Consumes the value of the cell in column {@code column} and row of the
	 * actual cursor.
	 * 
	 * @param column
	 *            index of the column starting with 0.
	 */
	public void column(int column);

	/**
	 * Moves the cursor {@code steps} steps up.
	 * 
	 * @param steps
	 *            number of steps up.
	 * @return a new Cursor {@code steps} steps above the actual cursor.
	 */
	public ReferenceCell<T> goUp(int steps);

	/**
	 * Moves the cursor {@code steps} steps down.
	 * 
	 * @param steps
	 *            number of steps down.
	 * @return a new Cursor {@code steps} steps below the actual cursor.
	 */
	public ReferenceCell<T> goDown(int steps);

	/**
	 * Moves the cursor {@code steps} steps to the left.
	 * 
	 * @param steps
	 *            number of steps to the left.
	 * @return a new Cursor {@code steps} steps to the left the actual cursor.
	 */
	public ReferenceCell<T> goLeft(int steps);

	/**
	 * Moves the cursor {@code steps} steps to the right.
	 * 
	 * @param steps
	 *            number of steps to the right.
	 * @return a new Cursor {@code steps} steps to the right the actual cursor.
	 */
	public ReferenceCell<T> goRight(int steps);

	/**
	 * Moves the cursor to row {@code row}.
	 * 
	 * @param row
	 *            index of the row starting with 0.
	 * @return a new Cursor with row {@code row} and column of the actual
	 *         cursor.
	 */
	public ReferenceCell<T> goToRow(int row);

	/**
	 * Moves the cursor to column {@code column}.
	 * 
	 * @param column
	 *            index of the column starting with 0.
	 * @return a new Cursor with column {@code column} and row of the actual
	 *         cursor.
	 */
	public ReferenceCell<T> goToColumn(int column);	
}
