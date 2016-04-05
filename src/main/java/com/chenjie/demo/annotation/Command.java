package com.chenjie.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Command {
	
	public String name();
	public String auth();

}
