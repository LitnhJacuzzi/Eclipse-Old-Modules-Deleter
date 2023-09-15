package org.litnhjacuzzi.eomd.util;

public enum DetectType {
	METADATA("Metadata(slow/accurate)"), 
	DIR_NAME("Directory name(fast/error-prone)");
	
	private final String description;
	
	DetectType(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
