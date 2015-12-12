package org.afraidoferrors.streamingtables.table;
/**
 * 
 * @author Martin Weik
 *
 * @param <T> Type of data that are stored
 */
public interface TableVector<T> {
	/**
	 * 
	 * @return the position of this Vector in this table, starting with 0.
	 */
	public int position();
	
	/**
	 * Gives the Cell at the given position inside this Vector
	 * @param position inside this Vector, starting with 0.
	 * @return the Cell at the given position inside this Vector
	 */
	public Cell<T> cellAt(int position);

}
