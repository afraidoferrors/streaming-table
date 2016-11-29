package org.afraidoferrors.streamo.demo.explode;
/**
 * Dummyclass to demonstrate functionality
 * @author Martin
 *
 */
public class JournalEntry {
	private String account;
	private String contraaccount;
	private String debitamount;
	private String creditamount;
	private String text;
	
	public JournalEntry(String account, String contraaccount, String debitamount, String creditamount, String text) {
		this.account = account;
		this.contraaccount = contraaccount;
		this.debitamount = debitamount;
		this.creditamount = creditamount;
		this.text = text;			
	}

	@Override
	public String toString() {
		return "JournalEntry [account=" + account + ", contraaccount=" + contraaccount + ", debitamount=" + debitamount
				+ ", creditamount=" + creditamount + ", text=" + text + "]";
	}
	
	
	
}
