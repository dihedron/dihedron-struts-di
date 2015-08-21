/*
 * Copyright (c) 2012-2015, Andrea Funto'. All rights reserved. See LICENSE for details.
 */

package org.dihedron.struts.jndi;

import java.lang.reflect.Field;

import javax.ejb.Local;
import javax.ejb.Remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrea Funto'
 */
public class WebSphereJndiNameResolver implements JndiNameResolver {
	
	/**
	 * The SLF4J logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(WebSphereJndiNameResolver.class);
	
	/**
	 * Creates the JNDI lookup string from the class of the EJB reference.
	 * 
	 * @param field
	 *   the EJB (as a Reflection field) whose JNDI name is to be created.
	 * 
	 * @see org.dihedron.struts.jndi.JndiNameResolver#resolveEjbJndiName(java.lang.reflect.Field)
	 */
	@Override
	public String resolveEjbJndiName(Field field) {		
		StringBuilder name = new StringBuilder();
		Class<?> clazz = field.getType();
		logger.info("contructing JNDI name for resource '{}'", clazz.getName());
		Remote remote = clazz.getAnnotation(Remote.class);
		Local local = clazz.getAnnotation(Local.class);
		if(remote != null || clazz.getSimpleName().endsWith("Remote")) {			
			name.append(clazz.getName());
			logger.info("resource is remote: '{}'", name.toString());
		} else if(local != null || clazz.getSimpleName().endsWith("Local")) {
			name.append("ejblocal:").append(clazz.getName());
			logger.info("resource is local: '{}'", name.toString());
		}
		return name.length() > 0 ? name.toString() : null;
	}
}
