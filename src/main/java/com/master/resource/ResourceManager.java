package com.master.resource;

import com.master.exception.MasterRuntimeException;

public class ResourceManager {

	private static final ThreadLocal<? super Resource> RESOURCE = new ThreadLocal<Resource>();
	
	public static Resource getResource() {
		Resource r = (Resource) RESOURCE.get();
		if(r == null){
			throw new MasterRuntimeException("Recursos n�o inicializados");
		}
		return r;
	}
	
	public static Resource init(){
		Resource r = (Resource) RESOURCE.get();
		if(r != null){
			throw new MasterRuntimeException("Recursos j� inicializados");
		}
		r = new ResourceImpl();
		RESOURCE.set(r);
		return r;
	}
	
	
	public static Resource finish(){
		Resource r = getResource();
		RESOURCE.remove();
		return r;
	}
	
	

}
