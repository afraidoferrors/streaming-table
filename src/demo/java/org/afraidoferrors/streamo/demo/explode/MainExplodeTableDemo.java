/**
 * 
 */
package org.afraidoferrors.streamo.demo.explode;

import org.afraidoferrors.streamingtables.table.Table;

/**
 * @author Martin
 * 
 *         Explodes a table into two Objects per row
 *
 */
public class MainExplodeTableDemo {

	/**
	 * An example for this input table:
	 * 
	 * <table summary="Sample Data">
	 * <tr>
	 * <th>Amount</th><th>Debit Account</th><th>Credit Account</th><th>Text</th>
	 * </tr>
	 * <tr>
	 * <td></td><td></td><td></td><td></td>
	 * </tr>
	 * </table>
	 * 
	 * @param args not used
	 */

	@SuppressWarnings("null")
	public static void main(String[] args) {
		// Load table
		Table<String> table = null;

		table.modelstream().workspace().onRows(row -> row.position() > 0).onColumns(col -> col.position() == 0)
				.collect(cc -> {
					cc.right(1);
					cc.right(2);
					cc.it();
					cc.value("");
					cc.right(3);
				}).workspace().restore().collect(cc -> {
					cc.right(2);
					cc.right(1);
					cc.value("");
					cc.it();
					cc.right(3);
				}).asList(q -> new JournalEntry(q.poll(), q.poll(), q.poll(), q.poll(), q.poll()));

		table.modelstream().
				// define the first workspace to work on
				workspace().
				// omit the first row
				onRows(row -> row.position() > 0).
				// allways set the cursor for workspace to the first column
				onColumns(col -> col.position() == 0).
				// Select Cells 1, 2, 3 and 4 in row
				collect(cc -> {
					//DEBIT account FIRST
					cc.right(1);
					//CREDIT account SECOND
					cc.right(2);
					//DEBIT AMOUNT
					cc.it();
					//CREDIT AMOUNT defaults to an empty String
					cc.value("");
					//TEXT
					cc.right(3);
				}).workspace().
				//restore predicates of workspace before (in this case: omit first row and work on first column)
				restore().
				// Select Cells 1, 2, 4 and 3 in row
				collect(cc -> {
					//CREDIT account FIRST
					cc.right(2);
					//DEBIT account SECOND
					cc.right(1);
					//DEBIT AMOUNT defaults to an empty String
					cc.value("");
					//CREDIT AMOUNT
					cc.it();
					//TEXT
					cc.right(3);
				}).
				//this is called twice per row as there are two selectstatements with the same workspace definition.
				asList(q -> new JournalEntry(q.poll(), q.poll(), q.poll(), q.poll(), q.poll()));
	}
	
	

}


