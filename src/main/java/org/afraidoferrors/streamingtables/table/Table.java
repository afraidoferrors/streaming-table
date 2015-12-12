package org.afraidoferrors.streamingtables.table;

import org.afraidoferrors.streamingtables.modeltable.ModelTable;

/**
 * /
 * Represents a table and offers functionality to extract data out of it.
 * 
 * Is immutable and stateless by definition.
 * 
 * @author Martin Weik
 * 
 * @param <T> Type of data that are stored
 */
public interface Table<T> {
	/**
	 * Create a {@link ModelTable} to extract objects out of tables.
	 * 
	 * @return a Table that allows extraction of Objects with a streaming API.
	 */
	public ModelTable<T> modelstream();

}
