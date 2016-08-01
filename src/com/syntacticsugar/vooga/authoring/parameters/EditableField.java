package com.syntacticsugar.vooga.authoring.parameters;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})


public @interface EditableField {

	public String inputLabel();
	public String defaultVal();
	
}
