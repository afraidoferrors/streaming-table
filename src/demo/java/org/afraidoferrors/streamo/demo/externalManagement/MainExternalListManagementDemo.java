package org.afraidoferrors.streamo.demo.externalManagement;

import java.util.ArrayList;
import java.util.logging.Logger;

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
	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		// Load table
		Table<String> table = null;
		
		table.modelstream().supply(MainExternalListManagementDemo::createModel);
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
				list.add(new DummyModel(value, number));
			}			
		} catch(Exception e) {
			//handle exception here
			//e.g logging
			log.severe("Error while interpreting value " + origValues + ", number " + origNumber + " in cell " + mq.row() + "/" + mq.column());
		}
	}


}
