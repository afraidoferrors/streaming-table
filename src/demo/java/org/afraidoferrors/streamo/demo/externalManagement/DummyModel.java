package org.afraidoferrors.streamo.demo.externalManagement;

public class DummyModel {
	private String value;
	private int number;
	public DummyModel(String value, int number) {
		this.value = value;
		this.number = number;
	}
	@Override
	public String toString() {
		return "DummyModel [value=" + value + ", number=" + number + "]";
	}
	
	

}
