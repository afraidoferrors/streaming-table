package org.afraidoferrors.streamingtables.modeltable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.afraidoferrors.streamingtables.table.Cell;

/**
 * A ModelTable serves as starting place to extract Objects out of tables.
 * It saves Workspaces that are used to define which Cells act as {@link ReferenceCell} to fill a queue {@link ModelQueue} that is forwarded to a Consumer. 
 * 
 * Defaults are:
 * <ul>
 * <li>every cell acts as ReferenceCell</li>
 * </ul>
 *  
 * @author Martin Weik
 *
 * @param <T>
 *            Type that is stored in every {@link Cell}
 */
public interface ModelTable<T> {

	/**
	 * Creates a new {@link Workspace} that defines the Cells on which the
	 * Functions in methods {@link ModelTable#asList(Function)} and
	 * {@link ModelTable#supply(Consumer)} are called.
	 * 
	 * @return a new {@link Workspace}.
	 */
	public Workspace<T> workspace();

	/**
	 * Applies the Function on every Cell defined by one or more
	 * {@link Workspace} and adds the result to a list. Does NOT make any
	 * assumption of the order of calling.
	 * 
	 * @param queueFunction The values selected by {@link ReferenceCell}
	 * @param <M> type of the returned objects.
	 * @return a List containing a Object of Type M for every selected Cell in  every {@link Workspace}.
	 */
	public <M> List<M> asList(Function<ModelQueue<T>, M> queueFunction);

	/**
	 * 
	 * Applies the Function on every Cell defined by one or more
	 * {@link Workspace}. The caller is obliged to collect data in his favored
	 * way. Does NOT make any assumption of the order of calling.
	 * 
	 * @param queue The values selected by {@link ReferenceCell}
	 */
	public void supply(Consumer<ModelQueue<T>> queue);

}
