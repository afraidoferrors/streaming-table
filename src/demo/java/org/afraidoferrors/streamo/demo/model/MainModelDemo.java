package org.afraidoferrors.streamo.demo.model;

import java.util.List;

import org.afraidoferrors.streamingtables.arraytable.ArrayTable;

/**
 * This Demo demonstrates the construction of objects from a table where the entities are distributed over 3 rows. 
 * @author Martin
 *
 */

public class MainModelDemo {

	
	public static void main(String[] args) {
		String[] days = { "Day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		String[] dish1code = { "Code", "a01", "a02", "a04", "a03", "a05" };
		String[] dish1name = { "Name", "Radish", "Bum", "Meat", "Vegetables", "Sweets" };
		String[] dish1price = { "Price", "1.2", "1.3", "1.4", "1.5", "1.6" };
		String[] dish2code = { "Code", "b03", "b02", "b04", "b05", "b07" };
		String[] dish2name = { "Name", "Horseradish", "Bunt", "Chicken", "Vegetables", "Pig" };
		String[] dish2price = { "Price", "1.2", "1.3", "1.4", "1.5", "1.6" };
		String[][] data = { days, dish1code, dish1name, dish1price, dish2code, dish2name, dish2price };

		ArrayTable<String> dishestable = new ArrayTable<>(data);

		List<Dish> dishes = dishestable.modelstream().workspace()
				//onRows restricts the cursor where the given Predicate is true (in this case: The cell in the first column equals "Code")
				.onRows(r -> r.cellAt(0).value().equals("Code"))
				//onColumns restricts the cursor where the given Predicate is true (in this case: All rows except the first one)
				.onColumns(c -> c.position() > 0)
				//tell the library the cells to visit relative to the cursors position
				.collect(cc -> {
					cc.row(0);
					cc.it();
					cc.down(1);
					cc.down(2);
				}).
				//asList executes the given Function for every dataset and returns the results as List
				asList(q -> new Dish(q.poll(), q.poll(), q.poll(), q.poll()));

		for (Dish dish : dishes) {
			System.out.println(dish);
		}
	

	}

}
