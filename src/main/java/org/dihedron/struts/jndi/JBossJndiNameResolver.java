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
 * This class resolves names of EJBs on JBoss, given the field where the EJB reference 
 * should be injected.<br>The EJB JNDI naming scheme is largely application-server-dependent, 
 * so we need a class to resolve names automatically in order to ease the user's task. This
 * class attempts an educated guess by assuming that the beans' names comply with the ordinary 
 * naming convention, so that <code>MyBean</code>'s remote interface is called 
 * </code>MyBean<u>Remote</u></code> and its local interface is <code>MyBean<u>Local</u></code>.<br>  
 * JBoss JNDI names (on version 7) adhere to the
 * <code>java:module/&lt;bean-name&gt;!&lt;fully-qualified-interface-name&gt;</code> pattern;
 * an example of one such name is <code>java:module/MyBean!org.dihedron.template.ejb.MyBeanRemote</code>
 * (JNDI binding of <code>MyBean</code>'s remote interface). 
 * 
 * @author Andrea Funto'
 *
 */
public class JBossJndiNameResolver implements JndiNameResolver {

	/**
	 * The SLF4J logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(JBossJndiNameResolver.class);
	
	/**
	 * @see org.dihedron.struts.jndi.JndiNameFactory#resolveJndiName(org.dihedron.struts.autowiring.AutoWired, org.dihedron.struts.autowiring.AutoWired.Type, java.lang.reflect.Field)
	 */
	@Override
	public String resolveEjbJndiName(Field field) {
		StringBuilder name = new StringBuilder();
		Class<?> clazz = field.getType();
		Remote remote = clazz.getAnnotation(Remote.class);
		Local local = clazz.getAnnotation(Local.class);
		if(remote != null || field.getType().getSimpleName().endsWith("Remote")) {
			logger.debug("resolving name for remote EJB '{}'", field.getType().getSimpleName());
			// java:module/ExampleBean!org.dihedron.template.ejb.ExampleBeanRemote
			String bean = field.getType().getSimpleName().replaceAll("Remote", "");
			name.append("java:module/").append(bean).append("!").append(field.getType().getName());
			logger.debug("resolved EJB: '{}'", name.toString());
		} else if(local != null || field.getType().getSimpleName().endsWith("Local")) {
			logger.debug("resolving name for local EJB '{}'", field.getType().getSimpleName());
			// java:module/ExampleBean!org.dihedron.template.ejb.ExampleBeanLocal
			String bean = field.getType().getSimpleName().replaceAll("Local", "");
			name.append("java:module/").append(bean).append("!").append(field.getType().getName());
			logger.debug("resolved EJB: '{}'", name.toString());
		}
		return name.length() > 0 ? name.toString() : null;
	}
}
