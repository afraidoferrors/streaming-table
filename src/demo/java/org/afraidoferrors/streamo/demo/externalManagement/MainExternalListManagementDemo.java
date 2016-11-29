package org.afraidoferrors.streamo.demo.externalManagement;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.afraidoferrors.streamingtables.arraytable.ArrayTable;
import org.afraidoferrors.streamingtables.modeltable.ModelQueue;
import org.afraidoferrors.streamingtables.table.Table;

/**
 * Demonstrates management in an external list with a static method reference.
 * 
 * This feature gives the possibility to cope with multiple Objects or more complex structures (like maps) and exceptions. 
 * @author Martin
 *
 */
public class MainExternalListManagementDemo {

	private static ArrayList<DummyModel> list = new ArrayList<>();
	
	private static Logger log = Logger.getLogger(MainExternalListManagementDemo.class.getName());
	
	public static void main(String[] args) {
		// Load table, the table in this case
		String[][] data = new String[][] {
			new String[] {"Value1", "Value2/Value3"},
			new String[] {"123", "456"}
		};
		Table<String> table = new ArrayTable<>(data);
		table.modelstream().workspace().onRows(r -> r.position() == 0).collect(cc -> {cc.it();cc.down(1);}).supply(MainExternalListManagementDemo::createModel);
		
		list.forEach(System.out::println);
		
		System.out.println();
		
		//Bad style but this is just a demo
		data[1][1] = "456/789";
		list = new ArrayList<>();
		table.modelstream().workspace().onRows(r -> r.position() == 0).collect(cc -> {cc.it();cc.down(1);}).supply(MainExternalListManagementDemo::createModel);
		list.forEach(System.out::println);
	}
	
	private static void createModel(ModelQueue<String> mq) {
		String origValues = mq.poll();
		String[] values = origValues.split("/");
		String origNumber = mq.poll();
		int number;
		try {
			//values can have more values separated by "/"
			number = Integer.parseInt(origNumber);
			for(String value : values) {
				DummyModel dummy = new DummyModel(value, number);
				list.add(dummy);
			}			
		} catch(Exception e) {
			//handle exception here
			//e.g logging
			log.severe("Error while interpreting value " + origValues + ", number " + origNumber + " with cursor at cell " + mq.row() + "/" + mq.column());
		}
	}


}
