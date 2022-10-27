package br.puc.rio.model;

/**
 * 
 * Enumerator for action types.
 *
 */
public enum ActionType {
	RUNSQL("RunSql"), CUSTOM("Custom"),RUNSQLFILE("RunSqlFile");

	private final String description;
	
	/**
	 * Constructor Method
	 * @param description
	 */
	ActionType(String description) {
		this.description = description;
	}
	
	/**
	 * Get Description
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

}
