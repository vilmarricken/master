package com.master.xml.parser;

import java.lang.reflect.Constructor;

import com.master.exception.MasterException;

public class ParserObject {

	private Class<?> definition;
	
	private Constructor<?> constructor;
	
	public ParserObject(Class<?> definition) throws MasterException {
		this.definition = definition;
		try {
			this.constructor = this.definition.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			throw new MasterException("Error on read constructor of class " + this.definition.getClass(), e);
		}
	}
	
	public 
	
}
