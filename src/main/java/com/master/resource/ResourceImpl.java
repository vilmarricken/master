package com.master.resource;

import com.master.exception.MasterRuntimeException;

public class ResourceImpl implements Resource{

	private static final ThreadLocal<ResourceImpl> RESOURCE = new ThreadLocal<ResourceImpl>();

	public ResourceImpl() {
	}

	public static ResourceImpl getResource() {
		ResourceImpl r = RESOURCE.get();
		if(r == null){
			throw new MasterRuntimeException("Recursos n�o inicializados");
		}
		return r;
	}
	
	public static ResourceImpl init(){
		ResourceImpl r = RESOURCE.get();
		if(r != null){
			throw new MasterRuntimeException("Recursos j� inicializados");
		}
		r = new ResourceImpl();
		return r;
	}

}
