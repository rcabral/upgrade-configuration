package br.puc.rio.model;

/**
 * 
 * Enumerator for BuildInformation Status.
 *
 */
public enum Status {
	PARCIAL("parcial"), COMPLETE("complete");

	private final String description;
	
	/**
	 * Constructor Method
	 * @param description
	 */
	Status(String description) {
		this.description = description;
	}
	
	/**
	 * Get description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}
}
