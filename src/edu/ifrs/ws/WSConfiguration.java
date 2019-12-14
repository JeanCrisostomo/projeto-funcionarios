package edu.ifrs.ws;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath(value = "/api")
public class WSConfiguration extends Application {
	
	public WSConfiguration() { }
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(edu.ifrs.ws.Service.class);
	}
	
}
