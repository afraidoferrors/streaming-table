package org.afraidoferrors.streamingtables.modeltable;
/**
 * An Exception that is thrown if anything is wrong with the Workspace:
 * * CellCursor tries to access Cells that don't exist
 * * Header - if defined - is never found or Cells are found before first Header
 * * Footer - if defined - is never found or Cells are found after last Footer
 * @author Martin
 *
 */
public class WorkspaceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7958305026847187812L;

	public WorkspaceException(String message) {
		super(message);
	}
	
}
