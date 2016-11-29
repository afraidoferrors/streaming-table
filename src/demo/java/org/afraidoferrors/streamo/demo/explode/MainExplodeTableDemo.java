/**
 * 
 */
package org.afraidoferrors.streamo.demo.explode;

import java.util.List;

import org.afraidoferrors.streamingtables.arraytable.ArrayTable;
import org.afraidoferrors.streamingtables.table.Table;

/**
 * @author Martin
 * 
 *         Explodes a table into two Objects per row.
 *         In this case a Journal with two accounts per row is exploded to a Journal with one account per row.
 *
 */
public class MainExplodeTableDemo {

	/**
	 * An example for this input table:
	 * 
	 * <table summary="Sample Data">
	 * <tr>
	 * <th>Amount</th>
	 * <th>Debit Account</th>
	 * <th>Credit Account</th>
	 * <th>Text</th>
	 * </tr>
	 * <tr>
	 * <td>20.0</td>
	 * <td>021234</td>
	 * <td>041234</td>
	 * <td>Revenue</td>
	 * <td>10.0</td>
	 * <td>051234</td>
	 * <td>021234</td>
	 * <td>Expense</td>
	 * </tr>
	 * </table>
	 * 
	 * @param args
	 *            not used
	 */

	public static void main(String[] args) {
		// Load table
		String[][] data = new String[][] { 
			new String[] { "Amount", "Debit Account", "Credit Account", "Text" },
			new String[] { "20.0", "021234", "041234", "Revenue" },
			new String[] { "10.0", "051234", "021234", "Expense" }
			
		};

		Table<String> table = new ArrayTable<>(data);

		List<JournalEntry> list = table.modelstream().
		// define the first workspace to work on
				workspace().
				// omit the first row
				onRows(row -> row.position() > 0).
				// allways set the cursor for workspace to the first column
				onColumns(col -> col.position() == 0).
				// Select Cells 2, 3, 1 and 4 in row and set Credit Amount to zero
				collect(cc -> {
					// DEBIT account FIRST
					cc.right(1);
					// CREDIT account SECOND
					cc.right(2);
					// DEBIT AMOUNT
					cc.it();
					// CREDIT AMOUNT defaults to zero
					cc.value("0.00");
					// TEXT
					cc.right(3);
				}).workspace().
				// restore predicates of workspace before (in this case: omit
				// first row and work on first column)
				restore().
				// Select Cells 3, 2, 1 and 4 in row and set Debit Amount to zero
				collect(cc -> {
					// CREDIT account FIRST
					cc.right(2);
					// DEBIT account SECOND
					cc.right(1);
					// DEBIT AMOUNT defaults to zero
					cc.value("0.00");
					// CREDIT AMOUNT
					cc.it();
					// TEXT
					cc.right(3);
				}).
				// this is called twice per row as there are two
				// selectstatements with the same workspace definition.
				asList(q -> new JournalEntry(q.poll(), q.poll(), q.poll(), q.poll(), q.poll()));
		
		list.stream().forEach(e -> System.out.println(e));

	}

}
