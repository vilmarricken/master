package com.master.resource;

public class Resource {

	private static final ThreadLocal<Resource> RESOURCE = new ThreadLocal<Resource>();
	
	public Resource() {
	}

	public static Resource getResource(){
		return RESOURCE.get();
	}
	
}
