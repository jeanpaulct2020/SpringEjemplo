package com.jeandev.util;

public enum Skill {

	JAVA("Java"), NET("Net"), PYTHON("Python"), JAVASCRIPT("Javascript"), PHP("Php"), GROOVY("Groovy"), CPP("C++");
	
	private final String values;

	private Skill(String values) {
		this.values = values;
	}

	public String getDisplayValues() {
		return values;
	}
	
	
	
	
}
