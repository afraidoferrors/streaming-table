package org.afraidoferrors.streamingtables.arraytable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.afraidoferrors.streamingtables.modeltable.ReferenceCell;
import org.afraidoferrors.streamingtables.modeltable.ModelQueue;
import org.afraidoferrors.streamingtables.modeltable.ModelTable;
import org.afraidoferrors.streamingtables.modeltable.Workspace;
import org.afraidoferrors.streamingtables.table.Cell;
import org.afraidoferrors.streamingtables.table.Column;
import org.afraidoferrors.streamingtables.table.Row;

/**
 * Quick and dirty implementation. Workspaces not separated. Dumb algorithm to go
 * through cells.
 * 
 * @author Martin Weik
 *
 * @param <T> type of the values stored inside table
 */
public class ArrayModelTable<T> implements ModelTable<T>, Workspace<T> {

	T[][] data;

	private ArrayList<WorkspaceBundle<T>> bundles = new ArrayList<>();

	private WorkspaceBundle<T> actualBundle;

	public ArrayModelTable(T[][] data) {
		this.data = data;
	}

	@Override
	public Workspace<T> workspace() {
		this.actualBundle = new WorkspaceBundle<>();
		return this;
	}

	@Override
	public <M> List<M> asList(Function<ModelQueue<T>, M> queue) {
		ArrayList<M> list = new ArrayList<>();
		for (int rowNum = 0; rowNum < data.length; rowNum++) {
			for (int colNum = 0; colNum < data[rowNum].length; colNum++) {
				for (WorkspaceBundle<T> bundle : this.bundles) {
					// Check predicates
					boolean goOn = true;
					if (goOn) {
						goOn = bundle.onRows.test(new ArrayRow<>(rowNum, this.data));
					}
					if (goOn) {
						goOn = bundle.onColumns.test(new ArrayColumn<>(colNum, this.data));
					}
					if (goOn) {
						goOn = bundle.onCells.test(new ArrayCell<>(rowNum, colNum, this.data));
					}

					if (goOn) {
						final ArrayModelQueue<T> dataqueue = new ArrayModelQueue<T>(rowNum, colNum);

						bundle.cellCursor.accept(new ArrayCellCursor<T>(rowNum, colNum, data, dataqueue));
						// do stuff
						list.add(queue.apply(dataqueue));
					}
				}
			}
		}

		return list;
	}

	@Override
	public void supply(Consumer<ModelQueue<T>> queue) {
		for (int rowNum = 0; rowNum < data.length; rowNum++) {
			for (int colNum = 0; colNum < data[rowNum].length; colNum++) {
				for (WorkspaceBundle<T> bundle : this.bundles) {
					// Check predicates
					boolean goOn = true;
					if (goOn) {
						goOn = bundle.onRows.test(new ArrayRow<>(rowNum, this.data));
					}
					if (goOn) {
						goOn = bundle.onColumns.test(new ArrayColumn<>(colNum, this.data));
					}
					if (goOn) {
						goOn = bundle.onCells.test(new ArrayCell<>(rowNum, colNum, this.data));
					}

					if (goOn) {
						final ArrayModelQueue<T> dataqueue = new ArrayModelQueue<T>(rowNum, colNum);

						bundle.cellCursor.accept(new ArrayCellCursor<T>(rowNum, colNum, data, dataqueue));
						// do stuff
						queue.accept(dataqueue);
					}
				}
			}
		}

	}

	@Override
	public Workspace<T> onRows(Predicate<Row<T>> row) {
		this.actualBundle.onRows = row;
		return this;
	}

	@Override
	public Workspace<T> onColumns(Predicate<Column<T>> col) {
		this.actualBundle.onColumns = col;
		return this;
	}

	@Override
	public Workspace<T> onCells(Predicate<Cell<T>> cell) {
		this.actualBundle.onCells = cell;
		return this;
	}

	@Override
	public Workspace<T> restore() {
		this.actualBundle = this.bundles.get(this.bundles.size() - 1).copy();
		return this;
	}

	@Override
	public ModelTable<T> collect(Consumer<ReferenceCell<T>> cc) {
		this.actualBundle.cellCursor = cc;
		this.bundles.add(this.actualBundle);
		return this;
	}

	private static class ArrayModelQueue<T> implements ModelQueue<T> {

		private final int row;
		private final int column;
		private final LinkedList<T> queue;
		

		public ArrayModelQueue(int row, int column) {
			this.row = row;
			this.column = column;
			this.queue = new LinkedList<>();
		}

		@Override
		public int row() {
			return this.row;
		}

		@Override
		public int column() {
			return this.column;
		}

		@Override
		public T poll() {
			return this.queue.poll();
		}
		
		public void add(T value) {
			this.queue.add(value);
		}

	}

	private static class ArrayCellCursor<T> implements ReferenceCell<T> {

		private ArrayModelQueue<T> q;
		private int r;
		private int c;
		private T[][] data;

		public ArrayCellCursor(int row, int col, T[][] data, ArrayModelQueue<T> queue) {
			this.r = row;
			this.c = col;
			this.data = data;
			this.q = queue;
		}

		@Override
		public void value(T value) {
			q.add(value);
		}

		@Override
		public void it() {
			q.add(data[r][c]);
		}

		@Override
		public void up(int steps) {
			q.add(data[r - steps][c]);

		}

		@Override
		public void down(int steps) {
			q.add(data[r + steps][c]);

		}

		@Override
		public void left(int steps) {
			q.add(data[r][c - steps]);

		}

		@Override
		public void right(int steps) {
			q.add(data[r][c + steps]);

		}

		@Override
		public void row(int row) {
			q.add(data[row][c]);

		}

		@Override
		public void column(int column) {
			q.add(data[r][column]);

		}

		@Override
		public ReferenceCell<T> goUp(int steps) {
			return new ArrayCellCursor<T>(r - steps, c, data, q);
		}

		@Override
		public ReferenceCell<T> goDown(int steps) {
			return new ArrayCellCursor<T>(r + steps, c, data, q);
		}

		@Override
		public ReferenceCell<T> goLeft(int steps) {
			return new ArrayCellCursor<T>(r, c - steps, data, q);
		}

		@Override
		public ReferenceCell<T> goRight(int steps) {
			return new ArrayCellCursor<T>(r, c + steps, data, q);
		}

		@Override
		public ReferenceCell<T> goToRow(int row) {
			return new ArrayCellCursor<T>(row, c, data, q);
		}

		@Override
		public ReferenceCell<T> goToColumn(int column) {
			return new ArrayCellCursor<T>(r, column, data, q);
		}

	}

}
