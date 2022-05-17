package br.puc.rio.model;

public enum Status {
	PARCIAL("parcial"), COMPLETE("complete");

	private final String description;

	Status(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
