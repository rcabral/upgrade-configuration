package br.puc.rio.model;

public enum ActionType {
	RUNSQL("RunSql"), CUSTOM("Custom"),RUNSQLFILE("RunSqlFile");

	private final String description;

	ActionType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
