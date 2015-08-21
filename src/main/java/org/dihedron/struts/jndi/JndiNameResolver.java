/*
 * Copyright (c) 2012-2015, Andrea Funto'. All rights reserved. See LICENSE for details.
 */

package org.dihedron.struts.jndi;

import java.lang.reflect.Field;

/**
 * This is the base interface for all classes implementing the mechanism
 * to intelligently generate the EJB binding name for an EJB of which
 * only the local/remote interface class is known.
 * 
 * @author Andrea Funto'
 */
public interface JndiNameResolver {
	/**
	 * Creates an application server-specific JNDI name for the given EJB.
	 * 
	 * @param field
	 *   the EJB (as a Reflection field) whose JNDI name is to be created.
	 * @return
	 *   the JNDI name for the given EJB.
	 */
	String resolveEjbJndiName(Field field);
}
